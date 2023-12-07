package com.aia.amq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aia.amq.dto.WalletChangeDto;
import com.aia.enums.AmqEnums;
import com.aia.finance.entity.MemberWalletEntity;
import com.aia.finance.entity.MemberWalletRecordEntity;
import com.aia.finance.service.AgentWalletRecordService;
import com.aia.finance.service.AgentWalletService;
import com.aia.finance.service.MemberWalletRecordService;
import com.aia.finance.service.MemberWalletService;
import com.aia.system.entity.SysLogEntity;
import com.aia.util.AmqUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

/** 
 * 钱包处理相关消息队列消费端
* @author yangxy
* @version 创建时间：2023年11月28日 上午10:37:24 
*/
@Slf4j
@Component
public class WalletConsumer {
	@Autowired
	private AmqUtils amqUtils;
	@Autowired
	private MemberWalletService memberWalletService;
	@Autowired
	private MemberWalletRecordService memberWalletRecordService;
	@Autowired
	private AgentWalletService agentWalletService;
	@Autowired
	private AgentWalletRecordService agentWalletRecordService;
	
	@RabbitListener(queues = "#{walletChangeQueue.name}",concurrency = "10")
	public void walletChangeQueueListener(String message) {
		log.info("消费钱包余额变动消息：{}",message);
		WalletChangeDto walletChangeDto = JSONObject.toJavaObject(JSON.parseObject(message), WalletChangeDto.class);
		if(walletChangeDto.getUserType() == 0) {//会员
			MemberWalletEntity memberWalletEntity = memberWalletService.getOne(new QueryWrapper<MemberWalletEntity>().lambda().eq(MemberWalletEntity::getMemberId, walletChangeDto.getUserId()));
			if(walletChangeDto.getType() == 2 || walletChangeDto.getType() == 4) {//2充值 4从游戏商转入
				walletChangeDto.setWalletId(memberWalletEntity.getId());
				walletChangeDto.setBeforeBalance(memberWalletEntity.getBalance());
				memberWalletEntity.setBalance(memberWalletEntity.getBalance() + walletChangeDto.getAmt());
				walletChangeDto.setAfterBalance(memberWalletEntity.getBalance());
				memberWalletService.updateById(memberWalletEntity);
			}else if(walletChangeDto.getType() == 1 || walletChangeDto.getType() == 3) {//1提现 3转出到游戏商
				walletChangeDto.setWalletId(memberWalletEntity.getId());
				walletChangeDto.setBeforeBalance(memberWalletEntity.getBalance());
				memberWalletEntity.setBalance(memberWalletEntity.getBalance() - walletChangeDto.getAmt());
				walletChangeDto.setAfterBalance(memberWalletEntity.getBalance());
				memberWalletService.updateById(memberWalletEntity);
			}
		}else if(walletChangeDto.getUserType() == 1) {//代理
			
		}
		amqUtils.sendMqMsg(AmqEnums.WALLET_CHANGE_RECORD_AMQ.exchangeName, AmqEnums.WALLET_CHANGE_RECORD_AMQ.routeKey, JSON.toJSONString(walletChangeDto));
	}
	
	@RabbitListener(queues = "#{walletChangeRecordQueue.name}",concurrency = "10")
	public void walletChangeRecordQueueListener(String message) {
		log.info("消费消费钱包余额变动记录消息：{}",message);
		WalletChangeDto walletChangeDto = JSONObject.toJavaObject(JSON.parseObject(message), WalletChangeDto.class);
		
		if(walletChangeDto.getUserType() == 0) {//会员
			MemberWalletRecordEntity memberWalletRecordEntity = new MemberWalletRecordEntity();
			memberWalletRecordEntity.setMemberWalletId(walletChangeDto.getWalletId());
			memberWalletRecordEntity.setChangeAmout(walletChangeDto.getAmt());
			memberWalletRecordEntity.setBeforeBalance(walletChangeDto.getBeforeBalance());
			memberWalletRecordEntity.setAfterBalance(walletChangeDto.getAfterBalance());
			memberWalletRecordEntity.setType(walletChangeDto.getType());
			memberWalletRecordEntity.setRemark(walletChangeDto.getRemark());
			memberWalletRecordService.save(memberWalletRecordEntity);
		}else if(walletChangeDto.getUserType() == 1) {//代理
		}
	}
}
