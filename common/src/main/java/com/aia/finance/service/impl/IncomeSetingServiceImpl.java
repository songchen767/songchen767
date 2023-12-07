package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.StatusEnum;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.entity.IncomeSetingEntity;
import com.aia.finance.mapper.IncomeSetingMapper;
import com.aia.finance.req.IncomeSettingAddReq;
import com.aia.finance.req.IncomeSettingEditReq;
import com.aia.finance.req.IncomeSettingPageReq;
import com.aia.finance.req.IncomeSettingUpdateStatusReq;
import com.aia.finance.service.IncomeSetingService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 充值配置管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class IncomeSetingServiceImpl extends ServiceImpl<IncomeSetingMapper, IncomeSetingEntity> implements IncomeSetingService {

    @Override
    public ApiResp<String> updateStatusByChannelId(Long channelId, Integer status) {
        LambdaQueryWrapper<IncomeSetingEntity> lambdaQuery = new QueryWrapper<IncomeSetingEntity>().lambda();
        lambdaQuery.eq(IncomeSetingEntity::getChannelId,channelId);
        List<IncomeSetingEntity> list = list(lambdaQuery);
        List<IncomeSetingEntity> newList=new ArrayList<>();
        for(IncomeSetingEntity item:list){
            item.setStatus(status);
            newList.add(item);
        }
        saveOrUpdateBatch(newList);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<PageResp<IncomeSetingEntity>> page(IncomeSettingPageReq incomeSettingPageReq) {
        LambdaQueryWrapper<IncomeSetingEntity> lambdaQuery = new QueryWrapper<IncomeSetingEntity>().lambda();

        if (!StringUtils.isEmpty(incomeSettingPageReq.getChannelId())) {
            lambdaQuery.like(IncomeSetingEntity::getChannelId, incomeSettingPageReq.getChannelId());
        }
        if (!ObjectUtils.isEmpty(incomeSettingPageReq.getType())) {
            lambdaQuery.eq(IncomeSetingEntity::getType, incomeSettingPageReq.getType());
        }
        PageHelper.startPage(incomeSettingPageReq.getPageNo(), incomeSettingPageReq.getPageSize());
        Page<IncomeSetingEntity> page = (Page<IncomeSetingEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(IncomeSettingAddReq incomeSettingAddReq) {
        IncomeSetingEntity entity =new IncomeSetingEntity();
        BeanUtils.copyProperties(incomeSettingAddReq,entity);
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(IncomeSettingEditReq incomeSettingEditReq) {
        IncomeSetingEntity inComeSetingEntity = getById(incomeSettingEditReq.getId());
        BeanUtils.copyProperties(incomeSettingEditReq,inComeSetingEntity);
        saveOrUpdate(inComeSetingEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<List<IncomeSetingEntity>> queryByChannelId(Long channelId) {
        LambdaQueryWrapper<IncomeSetingEntity> lambdaQuery = new QueryWrapper<IncomeSetingEntity>().lambda();
        lambdaQuery.eq(IncomeSetingEntity::getChannelId,channelId);
        lambdaQuery.eq(IncomeSetingEntity::getStatus, StatusEnum.ON.getValue());
        List<IncomeSetingEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }

    @Override
    public ApiResp<String> updateStatus(IncomeSettingUpdateStatusReq incomeSettingUpdateStatusReq) {
        IncomeSetingEntity incomeSetingEntity = getById(incomeSettingUpdateStatusReq.getId());
        incomeSetingEntity.setStatus(incomeSettingUpdateStatusReq.getStatus());
        updateById(incomeSetingEntity);
        return ApiResp.sucess();
    }
}
