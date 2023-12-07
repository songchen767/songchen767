package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.mapper.CashChannelMapper;
import com.aia.finance.req.*;
import com.aia.finance.service.CashChannelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 提现渠道管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class CashChannelServiceImpl extends ServiceImpl<CashChannelMapper, CashChannelEntity> implements CashChannelService {

    @Override
    public ApiResp<PageResp<CashChannelEntity>> page(CashChannelPageReq cashChannelPageReq) {
        LambdaQueryWrapper<CashChannelEntity> lambdaQuery = new QueryWrapper<CashChannelEntity>().lambda();

        if (!StringUtils.isEmpty(cashChannelPageReq.getName())) {
            lambdaQuery.like(CashChannelEntity::getName, cashChannelPageReq.getName());
        }
        if (!ObjectUtils.isEmpty(cashChannelPageReq.getType())) {
            lambdaQuery.eq(CashChannelEntity::getType, cashChannelPageReq.getType());
        }
        if (!ObjectUtils.isEmpty(cashChannelPageReq.getStatus())) {
            lambdaQuery.eq(CashChannelEntity::getStatus, cashChannelPageReq.getStatus());
        }
        PageHelper.startPage(cashChannelPageReq.getPageNo(), cashChannelPageReq.getPageSize());
        Page<CashChannelEntity> page = (Page<CashChannelEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(CashChannelAddReq cashChannelAddReq) {
        LambdaQueryWrapper<CashChannelEntity> lambdaQuery = new QueryWrapper<CashChannelEntity>().lambda();
        lambdaQuery.eq(CashChannelEntity::getName, cashChannelAddReq.getName());
        CashChannelEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("param name is repeat");
        }
        CashChannelEntity entity = new CashChannelEntity();
        BeanUtils.copyProperties(cashChannelAddReq, entity);
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(CashChannelEditReq cashChannelEditReq) {
        LambdaQueryWrapper<CashChannelEntity> lambdaQuery = new QueryWrapper<CashChannelEntity>().lambda();
        lambdaQuery.eq(CashChannelEntity::getName, cashChannelEditReq.getName());
        lambdaQuery.ne(CashChannelEntity::getId, cashChannelEditReq.getId());
        CashChannelEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("param name is repeat");
        }
        CashChannelEntity entity = new CashChannelEntity();
        BeanUtils.copyProperties(cashChannelEditReq, entity);
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(CashChannelStatusUpdateReq cashChannelStatusUpdateReq) {
        CashChannelEntity entity = getById(cashChannelStatusUpdateReq.getId());
        entity.setStatus(cashChannelStatusUpdateReq.getStatus());
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<List<CashChannelEntity>> getCashChannel() {
        LambdaQueryWrapper<CashChannelEntity> lambdaQuery = new QueryWrapper<CashChannelEntity>().lambda();
        lambdaQuery.eq(CashChannelEntity::getStatus, StatusEnum.ON.getValue());
        List<CashChannelEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }

    @Override
    public CashChannelEntity getCashChannelById(Long id) {
        CashChannelEntity entity = getById(id);
        return entity;
    }

    @Override
    public ApiResp<String> channelBindcash(ChannelBindCashReq channelBindCashReq) {

        return null;
    }
}
