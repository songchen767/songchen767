package com.aia.finance.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.mapper.IncomeChannelMapper;
import com.aia.finance.req.IncomeChannelAddReq;
import com.aia.finance.req.IncomeChannelEditReq;
import com.aia.finance.req.IncomeChannelPageReq;
import com.aia.finance.req.IncomeChannelStatusUpdateReq;
import com.aia.finance.service.IncomeChannelService;
import com.aia.finance.service.IncomeSetingService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 充值渠道管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class IncomeChannelServiceImpl extends ServiceImpl<IncomeChannelMapper, IncomeChannelEntity> implements IncomeChannelService {

    @Autowired
    private IncomeSetingService incomeSettingService;

    @Override
    public ApiResp<PageResp<IncomeChannelEntity>> page(IncomeChannelPageReq incomeChannelPageReq) {
        LambdaQueryWrapper<IncomeChannelEntity> lambdaQuery = new QueryWrapper<IncomeChannelEntity>().lambda();

        if (!StringUtils.isEmpty(incomeChannelPageReq.getName())) {
            lambdaQuery.like(IncomeChannelEntity::getName, incomeChannelPageReq.getName());
        }
        if (!ObjectUtils.isEmpty(incomeChannelPageReq.getType())) {
            lambdaQuery.eq(IncomeChannelEntity::getType, incomeChannelPageReq.getType());
        }
        if (!ObjectUtils.isEmpty(incomeChannelPageReq.getStatus())) {
            lambdaQuery.eq(IncomeChannelEntity::getStatus, incomeChannelPageReq.getStatus());
        }
        PageHelper.startPage(incomeChannelPageReq.getPageNo(), incomeChannelPageReq.getPageSize());
        Page<IncomeChannelEntity> page = (Page<IncomeChannelEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(IncomeChannelAddReq incomeChannelAddReq) {
        LambdaQueryWrapper<IncomeChannelEntity> lambdaQuery = new QueryWrapper<IncomeChannelEntity>().lambda();
        lambdaQuery.eq(IncomeChannelEntity::getName, incomeChannelAddReq.getName());
        IncomeChannelEntity incomeChannelEntityByName = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(incomeChannelEntityByName)) {
            throw new BusinessException("this param [name] is repeat");
        }
        IncomeChannelEntity incomeChannelEntity = new IncomeChannelEntity();
        BeanUtils.copyProperties(incomeChannelAddReq, incomeChannelEntity);
        save(incomeChannelEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(IncomeChannelEditReq incomeChannelEditReq) {
        LambdaQueryWrapper<IncomeChannelEntity> lambdaQueryByUserName = new QueryWrapper<IncomeChannelEntity>().lambda();
        lambdaQueryByUserName.eq(IncomeChannelEntity::getName, incomeChannelEditReq.getName());
        IncomeChannelEntity oneByName = getOne(lambdaQueryByUserName);
        if (!ObjectUtils.isEmpty(oneByName)) {
            throw new BusinessException("this name has existed");
        }
        IncomeChannelEntity entity = new IncomeChannelEntity();
        BeanUtils.copyProperties(incomeChannelEditReq, entity);
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    @Transactional
    public ApiResp<String> updateStatus(IncomeChannelStatusUpdateReq incomeChannelStatusUpdateReq) {
        IncomeChannelEntity incomeChannelEntity = getById(incomeChannelStatusUpdateReq.getChannelId());
        incomeChannelEntity.setStatus(incomeChannelStatusUpdateReq.getStatus());
        saveOrUpdate(incomeChannelEntity);
        if (StatusEnum.OFF.getValue().equals(incomeChannelStatusUpdateReq.getStatus())) {
            //停用配置
            incomeSettingService.updateStatusByChannelId(incomeChannelStatusUpdateReq.getChannelId(), incomeChannelStatusUpdateReq.getStatus());
        }

        return ApiResp.sucess();
    }

    @Override
    public ApiResp<List<IncomeChannelEntity>> channelList() {
        LambdaQueryWrapper<IncomeChannelEntity> lambdaQuery = new QueryWrapper<IncomeChannelEntity>().lambda();
        lambdaQuery.eq(IncomeChannelEntity::getStatus, 1);
        List<IncomeChannelEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }
}
