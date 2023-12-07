package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.MemberWalletEntity;
import com.aia.finance.entity.MemberWalletRecordEntity;
import com.aia.finance.mapper.MemberWalletRecordMapper;
import com.aia.finance.req.MemberWalletRecordPageReq;
import com.aia.finance.service.MemberWalletRecordService;
import com.aia.finance.service.MemberWalletService;
import com.aia.user.entity.MemberEntity;
import com.aia.user.service.MemberService;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员钱包记录 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MemberWalletRecordServiceImpl extends ServiceImpl<MemberWalletRecordMapper, MemberWalletRecordEntity> implements MemberWalletRecordService {

    @Autowired
    private MemberWalletService memberWalletService;

    @Override
    public ApiResp<PageResp<MemberWalletRecordEntity>> page(MemberWalletRecordPageReq memberWalletRecordPageReq) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        LambdaQueryWrapper<MemberWalletEntity> query = new QueryWrapper<MemberWalletEntity>().lambda();
        query.eq(MemberWalletEntity::getMemberId, loginUserId);
        MemberWalletEntity memberWalletEntity = memberWalletService.getOne(query);
        LambdaQueryWrapper<MemberWalletRecordEntity> lambdaQueryWrapper = new QueryWrapper<MemberWalletRecordEntity>().lambda();
        lambdaQueryWrapper.eq(MemberWalletRecordEntity::getMemberWalletId, memberWalletEntity.getId());
        PageHelper.startPage(memberWalletRecordPageReq.getPageNo(), memberWalletRecordPageReq.getPageSize());
        Page<MemberWalletRecordEntity> page = (Page<MemberWalletRecordEntity>) list(lambdaQueryWrapper);
        return ApiResp.page(page);
    }
}
