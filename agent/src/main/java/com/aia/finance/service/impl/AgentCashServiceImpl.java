package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.dto.LoginDto;
import com.aia.enums.ChannelTypeEnum;
import com.aia.exception.ApiBussException;
import com.aia.finance.entity.AgentCashEntity;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.entity.AgentWalletEntity;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.mapper.AgentCashMapper;
import com.aia.finance.req.AgentCashEditReq;
import com.aia.finance.req.ChannelBindCashReq;
import com.aia.finance.service.AgentCashRecordService;
import com.aia.finance.service.AgentCashService;
import com.aia.finance.service.AgentWalletService;
import com.aia.finance.service.CashChannelService;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 代理提现管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentCashServiceImpl extends ServiceImpl<AgentCashMapper, AgentCashEntity> implements AgentCashService {

    @Autowired
    private CashChannelService cashChannelService;

    @Override
    public ApiResp<AgentCashEntity> getCash(Long channeId) {
        LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
        LambdaQueryWrapper<AgentCashEntity> lambdaQueryWrapper = new QueryWrapper<AgentCashEntity>().lambda();
        lambdaQueryWrapper.eq(AgentCashEntity::getAgentId, loginUser.getUserId());
        lambdaQueryWrapper.eq(AgentCashEntity::getCashChannelId, channeId);
        AgentCashEntity one = getOne(lambdaQueryWrapper);
        return ApiResp.sucess(one);
    }

    @Override
    public ApiResp<String> channelBindCash(ChannelBindCashReq channelBindCashReq) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        CashChannelEntity channelEntity = cashChannelService.getById(channelBindCashReq.getChannelId());
        if (!checkRelationship(channelBindCashReq.getChannelId(),loginUserId,channelBindCashReq.getCashAccount())){
            throw new ApiBussException("this channel and cashAccount is existed");
        }
        AgentCashEntity entity = new AgentCashEntity();
        entity.setAgentId(loginUserId);
        entity.setCashChannelId(channelBindCashReq.getChannelId());
        if (ChannelTypeEnum.CARD.getValue().equals(channelEntity.getType())) {
            entity.setBankName(channelBindCashReq.getBankName());
            entity.setBankCardNo(channelBindCashReq.getCashAccount());
        } else if (ChannelTypeEnum.ALI_PAY.getValue().equals(channelEntity.getType())) {
            entity.setAlipay(channelBindCashReq.getCashAccount());
        } else if (ChannelTypeEnum.TENGXUN_PAY.getValue().equals(channelEntity.getType())) {
            entity.setWechat(channelBindCashReq.getCashAccount());
        } else if (ChannelTypeEnum.VM_PAY.getValue().equals(channelEntity.getType())) {
            entity.setCoin(channelBindCashReq.getCashAccount());
        }
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> edit(AgentCashEditReq agentCashEditReq) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (!checkRelationship(agentCashEditReq.getChannelId(),loginUserId,agentCashEditReq.getCashAccount())){
            throw new ApiBussException("this channel and cashAccount is existed");
        }
        AgentCashEntity entity = getById(agentCashEditReq.getCashId());
        //
        CashChannelEntity channelEntity = cashChannelService.getById(agentCashEditReq.getChannelId());
        entity.setAgentId(loginUserId);
        entity.setCashChannelId(agentCashEditReq.getChannelId());
        if (ChannelTypeEnum.CARD.getValue().equals(channelEntity.getType())) {
            entity.setBankName(agentCashEditReq.getBankName());
            entity.setBankCardNo(agentCashEditReq.getCashAccount());
        } else if (ChannelTypeEnum.ALI_PAY.getValue().equals(channelEntity.getType())) {
            entity.setAlipay(agentCashEditReq.getCashAccount());
        } else if (ChannelTypeEnum.TENGXUN_PAY.getValue().equals(channelEntity.getType())) {
            entity.setWechat(agentCashEditReq.getCashAccount());
        } else if (ChannelTypeEnum.VM_PAY.getValue().equals(channelEntity.getType())) {
            entity.setCoin(agentCashEditReq.getCashAccount());
        }

        return ApiResp.sucess();
    }

    //判断渠道和账号绑定关系是否已经存在
    public boolean checkRelationship(Long channelId, Long agentId, String account) {
        LambdaQueryWrapper<AgentCashEntity> lambdaQueryWrapper = new QueryWrapper<AgentCashEntity>().lambda();
        lambdaQueryWrapper.eq(AgentCashEntity::getAgentId, agentId);
        lambdaQueryWrapper.eq(AgentCashEntity::getCashChannelId, channelId);
        lambdaQueryWrapper.or(i -> i.eq(AgentCashEntity::getBankCardNo, account)
                .or().eq(AgentCashEntity::getAlipay, account)
                .or().eq(AgentCashEntity::getWechat, account)
                .or().eq(AgentCashEntity::getCoin, account));
        AgentCashEntity one = getOne(lambdaQueryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return true;
        } else {
            return false;
        }


    }

}
