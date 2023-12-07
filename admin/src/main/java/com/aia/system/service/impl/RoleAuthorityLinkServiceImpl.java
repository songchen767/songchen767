package com.aia.system.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.system.req.RoleAuthAddReq;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aia.system.entity.RoleAuthorityLinkEntity;
import com.aia.system.mapper.RoleAuthorityLinkMapper;
import com.aia.system.service.RoleAuthorityLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxy
 * @version 创建时间：2023年11月7日 下午4:59:26
 */
@Service
public class RoleAuthorityLinkServiceImpl extends ServiceImpl<RoleAuthorityLinkMapper, RoleAuthorityLinkEntity> implements RoleAuthorityLinkService {


    @Override
    @Transactional
    public ApiResp<String> assignment(RoleAuthAddReq roleAuthAddReq) {
        LambdaQueryWrapper<RoleAuthorityLinkEntity> lambdaQuery = new QueryWrapper<RoleAuthorityLinkEntity>().lambda();
        lambdaQuery.eq(RoleAuthorityLinkEntity::getRoleId, roleAuthAddReq.getRoleId());
        if (CollectionUtils.isEmpty(roleAuthAddReq.getAuthorityIds())) {
            //这里表示删除所有权限
            remove(lambdaQuery);
        } else {
            //先删除再新增
            remove(lambdaQuery);
            List<Long> authorityIds = roleAuthAddReq.getAuthorityIds();
            List<RoleAuthorityLinkEntity> list=new ArrayList<>();
            for (Long item : authorityIds) {
                RoleAuthorityLinkEntity roleAuthorityLinkEntity=new RoleAuthorityLinkEntity();
                roleAuthorityLinkEntity.setRoleId(roleAuthAddReq.getRoleId());
                roleAuthorityLinkEntity.setAuthorityId(item);
                list.add(roleAuthorityLinkEntity);
            }
            saveBatch(list);
        }
        return ApiResp.sucess();
    }

    @Override
    public void add(List<RoleAuthorityLinkEntity> list) {

    }

    @Override
    public void batchDelByAuthId(List<Long> authIds) {

    }
}
