package com.aia.msg.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.BusinessException;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.msg.entity.NoteEntity;
import com.aia.msg.req.NoteTypeAddReq;
import com.aia.msg.req.NoteTypeEditReq;
import com.aia.msg.req.NoteTypePageReq;
import com.aia.msg.req.NoteTypeStatusEditReq;
import com.aia.msg.service.NoteService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aia.msg.entity.NoteTypeEntity;
import com.aia.msg.mapper.NoteTypeMapper;
import com.aia.msg.service.NoteTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 公告类型管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class NoteTypeServiceImpl extends ServiceImpl<NoteTypeMapper, NoteTypeEntity> implements NoteTypeService {


    @Autowired
    private NoteService noteService;

    @Override
    public ApiResp<PageResp<NoteTypeEntity>> page(NoteTypePageReq noteTypePageReq) {
        LambdaQueryWrapper<NoteTypeEntity> lambdaQuery = new QueryWrapper<NoteTypeEntity>().lambda();
        if (StringUtils.isNotEmpty(noteTypePageReq.getName())) {
            lambdaQuery.like(NoteTypeEntity::getName, noteTypePageReq.getName());
        }
        if (ObjectUtils.isNotEmpty(noteTypePageReq.getStatus())) {
            lambdaQuery.eq(NoteTypeEntity::getStatus, noteTypePageReq.getStatus());
        }
        PageHelper.startPage(noteTypePageReq.getPageNo(), noteTypePageReq.getPageSize());
        Page<NoteTypeEntity> page = (Page<NoteTypeEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(NoteTypeAddReq noteTypeAddReq) {
        LambdaQueryWrapper<NoteTypeEntity> lambdaQuery = new QueryWrapper<NoteTypeEntity>().lambda();
        if (StringUtils.isNotEmpty(noteTypeAddReq.getName())) {
            lambdaQuery.eq(NoteTypeEntity::getName, noteTypeAddReq.getName());
        }
        NoteTypeEntity entity = getOne(lambdaQuery);
        if (ObjectUtils.isNotEmpty(entity)) {
            throw new BusinessException("this type is repeat");
        }
        NoteTypeEntity noteTypeEntity = new NoteTypeEntity();
        BeanUtils.copyProperties(noteTypeAddReq, noteTypeEntity);
        save(noteTypeEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(NoteTypeEditReq noteTypeEditReq) {
        LambdaQueryWrapper<NoteTypeEntity> lambdaQuery = new QueryWrapper<NoteTypeEntity>().lambda();
        lambdaQuery.eq(NoteTypeEntity::getName, noteTypeEditReq.getName());
        lambdaQuery.ne(NoteTypeEntity::getId, noteTypeEditReq.getId());
        NoteTypeEntity entity = getOne(lambdaQuery);
        if (ObjectUtils.isNotEmpty(entity)) {
            throw new BusinessException("this type is repeat");
        }
//        entity.setStatus(noteTypeEditReq.getStatus());
////        entity.setName(noteTypeEditReq.getName());
        NoteTypeEntity noteTypeEntity = new NoteTypeEntity();
        noteTypeEntity.setName(noteTypeEditReq.getName());
        noteTypeEntity.setId(noteTypeEditReq.getId());
        updateById(noteTypeEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(NoteTypeStatusEditReq noteTypeStatusEditReq) {
        NoteTypeEntity entity = getById(noteTypeStatusEditReq.getId());
        entity.setStatus(noteTypeStatusEditReq.getStatus());
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<List<NoteTypeEntity>> typeList() {
        LambdaQueryWrapper<NoteTypeEntity> lambdaQuery = new QueryWrapper<NoteTypeEntity>().lambda();
        lambdaQuery.eq(NoteTypeEntity::getStatus, 1);
        List<NoteTypeEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }

    @Override
    public ApiResp<List<NoteTypeEntity>> queryNoteType() {
        List<NoteEntity> listApiResp = noteService.queryEffectiveNote();
        if (!CollectionUtils.isEmpty(listApiResp)) {
            Set<Long> ids=new HashSet<Long>();
            for (NoteEntity item : listApiResp) {
                ids.add(item.getNoteTypeId());
            }
            LambdaQueryWrapper<NoteTypeEntity> lambdaQuery = new QueryWrapper<NoteTypeEntity>().lambda();
            lambdaQuery.in(NoteTypeEntity::getId,ids);
            return ApiResp.sucess(list(lambdaQuery));
        }
        return null;
    }
}
