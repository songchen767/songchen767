package com.aia.system.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.BusinessException;
import com.aia.system.entity.AuthorityEntity;
import com.aia.system.req.RoleAddReq;
import com.aia.system.req.RoleEditReq;
import com.aia.system.req.RolePageReq;
import com.aia.system.req.RoleStatusEditReq;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aia.system.entity.RoleEntity;
import com.aia.system.mapper.RoleMapper;
import com.aia.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author yangxy
 * @version 创建时间：2023年11月7日 下午5:01:23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Override
    public ApiResp<PageResp<RoleEntity>> page(RolePageReq rolePageReq) {
        LambdaQueryWrapper<RoleEntity> lambdaQuery = new QueryWrapper<RoleEntity>().lambda();

        if (!StringUtils.isEmpty(rolePageReq.getRoleName())) {
            lambdaQuery.like(RoleEntity::getRoleName, rolePageReq.getRoleName());
        }
        if (!StringUtils.isEmpty(rolePageReq.getCode())) {
            lambdaQuery.eq(RoleEntity::getCode, rolePageReq.getCode());
        }
        if (!ObjectUtils.isEmpty(rolePageReq.getStatus())) {
            lambdaQuery.eq(RoleEntity::getStatus, rolePageReq.getStatus());
        }
        PageHelper.startPage(rolePageReq.getPageNo(), rolePageReq.getPageSize());
        Page<RoleEntity> page = (Page<RoleEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(RoleAddReq roleAddReq) {
        LambdaQueryWrapper<RoleEntity> lambdaQuery = new QueryWrapper<RoleEntity>().lambda();
        lambdaQuery.eq(RoleEntity::getCode, roleAddReq.getCode());
        RoleEntity roleEntityByCode = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(roleEntityByCode)) {
            throw new BusinessException("this param [code] is repeat");
        }
        LambdaQueryWrapper<RoleEntity> lambdaQuery2 = new QueryWrapper<RoleEntity>().lambda();
        lambdaQuery2.eq(RoleEntity::getRoleName, roleAddReq.getRoleName());
        RoleEntity roleByName = getOne(lambdaQuery2);
        if (!ObjectUtils.isEmpty(roleByName)) {
            throw new BusinessException("this param [roleName] is repeat");
        }
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleAddReq, roleEntity);
        save(roleEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(RoleEditReq roleEditReq) {
        RoleEntity roleEntity = getById(roleEditReq.getId());
        if (ObjectUtils.isEmpty(roleEntity)) {
            throw new BusinessException("data not exist");
        }
        LambdaQueryWrapper<RoleEntity> lambdaQuery = new QueryWrapper<RoleEntity>().lambda();
        lambdaQuery.eq(RoleEntity::getCode, roleEditReq.getCode());
        lambdaQuery.ne(RoleEntity::getId, roleEditReq.getId());
        RoleEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("this param [code] is repeat");
        }
        BeanUtils.copyProperties(roleEditReq, roleEntity);
        updateById(roleEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(RoleStatusEditReq roleStatusEditReq) {
        RoleEntity roleEntity = getById(roleStatusEditReq.getId());
        if (ObjectUtils.isEmpty(roleEntity)) {
            throw new BusinessException("data not exist");
        }
        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(roleEntity, role);
        role.setStatus(roleStatusEditReq.getStatus());
        updateById(role);
        return ApiResp.sucess();
    }
}
