package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.ChannelTypeEnum;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.entity.AgentWalletEntity;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.mapper.AgentCashRecordMapper;
import com.aia.finance.req.AgentCashPageReq;
import com.aia.finance.req.AgentCashRecordAddReq;
import com.aia.finance.service.AgentCashRecordService;
import com.aia.finance.service.AgentWalletService;
import com.aia.finance.service.CashChannelService;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 代理提现记录 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentCashRecordServiceImpl extends ServiceImpl<AgentCashRecordMapper, AgentCashRecordEntity> implements AgentCashRecordService {


    @Autowired
    private CashChannelService cashChannelService;

    @Autowired
    private AgentWalletService agentWalletService;

    @Override
    public ApiResp<PageResp<AgentCashRecordEntity>> page(AgentCashPageReq agentCashRequest) {
        LambdaQueryWrapper<AgentCashRecordEntity> lambdaQuery = new QueryWrapper<AgentCashRecordEntity>().lambda();

        if (!ObjectUtils.isEmpty(agentCashRequest.getCashChannelId())) {
            lambdaQuery.eq(AgentCashRecordEntity::getCashChannelId, agentCashRequest.getCashChannelId());
        }
        if (!ObjectUtils.isEmpty(agentCashRequest.getAgentId())) {
            lambdaQuery.eq(AgentCashRecordEntity::getAgentId, agentCashRequest.getAgentId());
        }
        if (!ObjectUtils.isEmpty(agentCashRequest.getAduitStatus())) {
            lambdaQuery.eq(AgentCashRecordEntity::getAduitStatus, agentCashRequest.getAduitStatus());
        }
        PageHelper.startPage(agentCashRequest.getPageNo(), agentCashRequest.getPageSize());
        Page<AgentCashRecordEntity> page = (Page<AgentCashRecordEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    @Transactional
    public ApiResp<String> add(AgentCashRecordAddReq agentCashRecordAddReq) {
        Long agentId = SecurityFrameworkUtils.getLoginUserId();
        CashChannelEntity cashChannelEntity = cashChannelService.getById(agentCashRecordAddReq.getChannelId());
        //添加代理积分记录
        AgentCashRecordEntity recordEntity = new AgentCashRecordEntity();
        recordEntity.setAgentId(agentId);
        recordEntity.setCashChannelId(agentCashRecordAddReq.getChannelId());
        if (ChannelTypeEnum.CARD.getValue().equals(cashChannelEntity.getType())) {
            recordEntity.setBankName(agentCashRecordAddReq.getBankName());
            recordEntity.setBankCardNo(agentCashRecordAddReq.getCashAccount());
        } else if (ChannelTypeEnum.ALI_PAY.getValue().equals(cashChannelEntity.getType())) {
            recordEntity.setAlipay(agentCashRecordAddReq.getCashAccount());
        } else if (ChannelTypeEnum.TENGXUN_PAY.getValue().equals(cashChannelEntity.getType())) {
            recordEntity.setWechat(agentCashRecordAddReq.getCashAccount());
        } else if(ChannelTypeEnum.VM_PAY.getValue().equals(cashChannelEntity.getType())) {
            recordEntity.setCoin(agentCashRecordAddReq.getCashAccount());
        }
        recordEntity.setAmount(agentCashRecordAddReq.getAdmount());
        save(recordEntity);
        // todo 向财务推送积分审核通知
        // 更新冻结金额
        AgentWalletEntity agentWalletInfo = agentWalletService.getByAgentId(agentId);
        agentWalletInfo.setBalance(agentWalletInfo.getBalance().subtract(agentCashRecordAddReq.getAdmount()));
        agentWalletInfo.setFreezeAmount(agentWalletInfo.getFreezeAmount().add(agentCashRecordAddReq.getAdmount()));
        agentWalletService.updateById(agentWalletInfo);
        return ApiResp.sucess();
    }


}
