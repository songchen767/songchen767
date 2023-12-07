package com.aia.personal.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.enums.ChannelTypeEnum;
import com.aia.enums.StatusEnum;
import com.aia.exception.ApiBussException;
import com.aia.exception.BusinessException;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.entity.MemberCashRecordEntity;
import com.aia.finance.entity.MemberWalletEntity;
import com.aia.finance.req.QueryReq;
import com.aia.finance.req.WithdrawalsReq;
import com.aia.finance.res.CashInfoRes;
import com.aia.finance.service.CashChannelService;
import com.aia.finance.service.MemberCashRecordService;
import com.aia.finance.service.MemberWalletService;
import com.aia.personal.entity.MemberBetRecordEntity;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aia.personal.entity.MemberCashEntity;
import com.aia.personal.mapper.MemberCashMapper;
import com.aia.personal.service.MemberCashService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 会员提现管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MemberCashServiceImpl extends ServiceImpl<MemberCashMapper, MemberCashEntity> implements MemberCashService {


    @Autowired
    private MemberWalletService memberWalletService;

    @Autowired
    private MemberCashRecordService memberCashRecordService;

    @Autowired
    private CashChannelService cashChannelService;

    @Override
    @Transactional
    public ApiResp<String> withdrawals(WithdrawalsReq withdrawalsReq) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        MemberWalletEntity memberWalletEntity = memberWalletService.getById(loginUserId);
        //1.校验会员余额是否满足积分金额
        if(memberWalletEntity.getBalance()<withdrawalsReq.getAmount()){
            throw new BusinessException("amount is too larger");
        }
        //2.todo 校验流水
        //3.添加会员积分记录
        CashChannelEntity cashChannel = cashChannelService.getById(withdrawalsReq.getChannel());
        if(StatusEnum.OFF.getValue()==cashChannel.getStatus()){
            throw new BusinessException("this channel status is off");
        }
        MemberCashRecordEntity entity=new MemberCashRecordEntity();
        entity.setCashChannelId(withdrawalsReq.getChannel());
        if (ChannelTypeEnum.CARD.getValue().equals(cashChannel.getType())) {
            entity.setBankCardNo(withdrawalsReq.getCashAccount());
        } else if (ChannelTypeEnum.ALI_PAY.getValue().equals(cashChannel.getType())) {
            entity.setAlipay(withdrawalsReq.getCashAccount());
        } else if (ChannelTypeEnum.TENGXUN_PAY.getValue().equals(cashChannel.getType())) {
            entity.setWechat(withdrawalsReq.getCashAccount());
        } else if(ChannelTypeEnum.VM_PAY.getValue().equals(cashChannel.getType())) {
            entity.setCoin(withdrawalsReq.getCashAccount());
        }
        entity.setMemberId(loginUserId);
        memberCashRecordService.save(entity);
        //4.todo  向财务推送积分审核通知
        //5.用户月减去相应金额并添加相应金额的冻结金额
        memberWalletEntity.setBalance(memberWalletEntity.getBalance()-withdrawalsReq.getAmount());
        memberWalletEntity.setFreezeAmount(memberWalletEntity.getFreezeAmount()+withdrawalsReq.getAmount());
        memberWalletService.updateById(memberWalletEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<List<CashInfoRes>> info(QueryReq queryReq) {
        LambdaQueryWrapper<MemberCashEntity> lambdaQueryWrapper = new QueryWrapper<MemberCashEntity>().lambda();
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        lambdaQueryWrapper.eq(MemberCashEntity::getCashChannelId,queryReq.getChanneId());
        lambdaQueryWrapper.eq(MemberCashEntity::getMemberId,loginUserId);
        List<MemberCashEntity> list = list(lambdaQueryWrapper);

        return ApiResp.sucess(list);
    }


}
