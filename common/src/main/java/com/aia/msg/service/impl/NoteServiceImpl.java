package com.aia.msg.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.NoteStatusEnum;
import com.aia.exception.BusinessException;
import com.aia.msg.entity.NoteTypeEntity;
import com.aia.msg.req.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aia.msg.entity.NoteEntity;
import com.aia.msg.mapper.NoteMapper;
import com.aia.msg.service.NoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 公告管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, NoteEntity> implements NoteService {

    @Override
    public ApiResp<PageResp<NoteEntity>> page(NotePageReq notePageReq) {
        LambdaQueryWrapper<NoteEntity> lambdaQuery = new QueryWrapper<NoteEntity>().lambda();
        if (ObjectUtils.isNotEmpty(notePageReq.getNoteTypeId())) {
            lambdaQuery.eq(NoteEntity::getNoteTypeId, notePageReq.getNoteTypeId());
        }
        if (ObjectUtils.isNotEmpty(notePageReq.getStatus())) {
            lambdaQuery.eq(NoteEntity::getStatus, notePageReq.getStatus());
        }
        PageHelper.startPage(notePageReq.getPageNo(), notePageReq.getPageSize());
        Page<NoteEntity> page = (Page<NoteEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(NoteAddReq noteAddReq) {
        LambdaQueryWrapper<NoteEntity> lambdaQuery = new QueryWrapper<NoteEntity>().lambda();
        lambdaQuery.eq(NoteEntity::getTitle, noteAddReq.getTitle());
        NoteEntity entity = getOne(lambdaQuery);
        if (ObjectUtils.isNotEmpty(entity)) {
            throw new BusinessException("this title is repeat");
        }
        NoteEntity noteEntity = new NoteEntity();
        BeanUtils.copyProperties(noteAddReq, noteEntity);
        save(noteEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(NoteEditReq noteEditReq) {
        LambdaQueryWrapper<NoteEntity> lambdaQuery = new QueryWrapper<NoteEntity>().lambda();
        lambdaQuery.eq(NoteEntity::getTitle, noteEditReq.getTitle());
        lambdaQuery.ne(NoteEntity::getId, noteEditReq.getId());
        NoteEntity entity = getOne(lambdaQuery);
        if (ObjectUtils.isNotEmpty(entity)) {
            throw new BusinessException("this title is repeat");
        }
        NoteEntity noteEntity = new NoteEntity();
        BeanUtils.copyProperties(noteEditReq, noteEntity);
        updateById(noteEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(NoteStatusEditReq noteStatusEditReq) {
        NoteEntity entity = getById(noteStatusEditReq.getId());
        if(NoteStatusEnum.PUBLISHED.getValue().equals(noteStatusEditReq.getStatus())){
            entity.setPublisher(noteStatusEditReq.getPublisher());
            entity.setPublishTime(LocalDateTime.now());
        }
        if(NoteStatusEnum.CANCEL.getValue().equals(noteStatusEditReq.getStatus())){
            entity.setCanceler(noteStatusEditReq.getCanceler());
            entity.setCancelTime(LocalDateTime.now());
        }
        entity.setStatus(noteStatusEditReq.getStatus());
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public List<NoteEntity> queryEffectiveNote() {
        LambdaQueryWrapper<NoteEntity> lambdaQuery = new QueryWrapper<NoteEntity>().lambda();
        lambdaQuery.eq(NoteEntity::getStatus,1);
        return list(lambdaQuery);
    }

    @Override
    public ApiResp<PageResp<NoteEntity>> queryNoteByType(NoteListReq noteListReq) {
        NotePageReq notePageReq=new NotePageReq();
        notePageReq.setNoteTypeId(noteListReq.getNoteTypeId());
        notePageReq.setPageNo(noteListReq.getPageNo());
        notePageReq.setPageSize(noteListReq.getPageSize());
        return page(notePageReq);
    }

    @Override
    public ApiResp<NoteEntity> queryNoteDetail(Long id) {
        return ApiResp.sucess(getById(id));
    }
}
