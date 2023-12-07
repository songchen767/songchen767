package com.aia.system.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.ApiBussException;
import com.aia.exception.BusinessException;
import com.aia.system.enums.AuthorityTypeEnum;
import com.aia.system.req.AuthorithEditReq;
import com.aia.system.req.AuthorityAddReq;
import com.aia.system.req.AuthorityPageReq;
import com.aia.system.req.AuthorityStatusEditReq;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aia.system.entity.AuthorityEntity;
import com.aia.system.mapper.AuthorityMapper;
import com.aia.system.service.AuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author yangxy
 * @version 创建时间：2023年11月7日 下午5:02:09
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityEntity> implements AuthorityService {


    @Override
    public ApiResp<PageResp<AuthorityEntity>> page(AuthorityPageReq authorityPageReq) {
        // TODO Auto-generated method stub
        LambdaQueryWrapper<AuthorityEntity> lambdaQuery = new QueryWrapper<AuthorityEntity>().lambda();

        if (!StringUtils.isEmpty(authorityPageReq.getAuthorityName())) {
            lambdaQuery.like(AuthorityEntity::getAuthorityName, authorityPageReq.getAuthorityName());
        }
        if (!StringUtils.isEmpty(authorityPageReq.getCode())) {
            lambdaQuery.eq(AuthorityEntity::getCode, authorityPageReq.getCode());
        }
        if (!ObjectUtils.isEmpty(authorityPageReq.getType())) {
            lambdaQuery.eq(AuthorityEntity::getType, authorityPageReq.getType());
        }
        if (!ObjectUtils.isEmpty(authorityPageReq.getStatus())) {
            lambdaQuery.eq(AuthorityEntity::getStatus, authorityPageReq.getStatus());
        }
        PageHelper.startPage(authorityPageReq.getPageNo(), authorityPageReq.getPageSize());
        Page<AuthorityEntity> page = (Page<AuthorityEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(AuthorityAddReq authorityAddReq) {
        LambdaQueryWrapper<AuthorityEntity> lambdaQuery = new QueryWrapper<AuthorityEntity>().lambda();
        lambdaQuery.eq(AuthorityEntity::getCode, authorityAddReq.getCode());
        AuthorityEntity authorityByCode = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(authorityByCode)) {
            throw new BusinessException("this param [code] is repeat");
        }
        LambdaQueryWrapper<AuthorityEntity> lambdaQuery2 = new QueryWrapper<AuthorityEntity>().lambda();
        lambdaQuery2.eq(AuthorityEntity::getAuthorityName, authorityAddReq.getAuthorityName());
        AuthorityEntity authorityByName = getOne(lambdaQuery2);
        if (!ObjectUtils.isEmpty(authorityByName)) {
            throw new BusinessException("this param [authorityName] is repeat");
        }
        AuthorityEntity authorityEntity = new AuthorityEntity();
        BeanUtils.copyProperties(authorityAddReq, authorityEntity);
        //校验参数:
        if(ObjectUtils.isEmpty(authorityAddReq.getParentId())){
            authorityEntity.setParentId(0L);
        }
        save(authorityEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(AuthorithEditReq authorityEditReq) {
        // TODO Auto-generated method stub
        AuthorityEntity authorityEntity = getById(authorityEditReq.getId());
        if (ObjectUtils.isEmpty(authorityEntity)) {
            throw new BusinessException("data not exist");
        }
        LambdaQueryWrapper<AuthorityEntity> lambdaQuery = new QueryWrapper<AuthorityEntity>().lambda();
        lambdaQuery.eq(AuthorityEntity::getCode, authorityEditReq.getCode());
        lambdaQuery.ne(AuthorityEntity::getId,authorityEditReq.getId());
        AuthorityEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("this param [code] is repeat");
        }
        //校验参数:
        if(ObjectUtils.isEmpty(authorityEditReq.getParentId())){
            authorityEntity.setParentId(0L);
        }
        BeanUtils.copyProperties(authorityEditReq, authorityEntity);
        updateById(authorityEntity);
        return ApiResp.sucess();
    }


    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(AuthorityStatusEditReq authorityStatusEditReq) {
        AuthorityEntity authorityEntity = getById(authorityStatusEditReq.getId());
        if(ObjectUtils.isEmpty(authorityEntity)){
            throw new BusinessException("data not exist");
        }
        AuthorityEntity auth=new AuthorityEntity();
        BeanUtils.copyProperties(authorityEntity,auth);
        auth.setStatus(authorityStatusEditReq.getStatus());
        updateById(auth);
        return ApiResp.sucess();
    }


}
