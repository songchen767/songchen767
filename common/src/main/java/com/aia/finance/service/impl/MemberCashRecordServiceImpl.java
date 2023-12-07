package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.entity.MemberCashRecordEntity;
import com.aia.finance.mapper.MemberCashRecordMapper;
import com.aia.finance.req.AgentCashPageReq;
import com.aia.finance.req.MemberCashRecordPageReq;
import com.aia.finance.service.MemberCashRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 会员提现记录 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MemberCashRecordServiceImpl extends ServiceImpl<MemberCashRecordMapper, MemberCashRecordEntity> implements MemberCashRecordService {


    public ApiResp<PageResp<MemberCashRecordEntity>> page(MemberCashRecordPageReq memberCashRecordPageReq){
        LambdaQueryWrapper<MemberCashRecordEntity> lambdaQuery = new QueryWrapper<MemberCashRecordEntity>().lambda();

        if(!ObjectUtils.isEmpty(memberCashRecordPageReq.getCashChannelId())) {
            lambdaQuery.eq(MemberCashRecordEntity::getCashChannelId, memberCashRecordPageReq.getCashChannelId());
        }
        if(!ObjectUtils.isEmpty(memberCashRecordPageReq.getMemberId())) {
            lambdaQuery.eq(MemberCashRecordEntity::getMemberId, memberCashRecordPageReq.getMemberId());
        }
        if(!ObjectUtils.isEmpty(memberCashRecordPageReq.getAduitStatus())) {
            lambdaQuery.eq(MemberCashRecordEntity::getAduitStatus, memberCashRecordPageReq.getAduitStatus());
        }
        PageHelper.startPage(memberCashRecordPageReq.getPageNo(), memberCashRecordPageReq.getPageSize());
        Page<MemberCashRecordEntity> page = (Page<MemberCashRecordEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }
}
