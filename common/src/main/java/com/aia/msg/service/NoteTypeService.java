package com.aia.msg.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.msg.entity.NoteTypeEntity;
import com.aia.msg.req.NoteTypeAddReq;
import com.aia.msg.req.NoteTypeEditReq;
import com.aia.msg.req.NoteTypePageReq;
import com.aia.msg.req.NoteTypeStatusEditReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公告类型管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface NoteTypeService extends IService<NoteTypeEntity> {

    /**
     * 公告类型分页查询
     * @param noteTypePageReq
     * @return
     */
    public ApiResp<PageResp<NoteTypeEntity>> page(NoteTypePageReq noteTypePageReq);

    /**
     * 公告类型新增
     * @param noteTypeAddReq
     * @return
     */
    ApiResp<String> add(NoteTypeAddReq noteTypeAddReq);

    /**
     * 公告类型修改
     * @param noteTypeEditReq
     * @return
     */
    ApiResp<String> eidt(NoteTypeEditReq noteTypeEditReq);

    /**
     * 公告类型状态更新
     * @param noteTypeStatusEditReq
     * @return
     */
    ApiResp<String> updateStatus(NoteTypeStatusEditReq noteTypeStatusEditReq);

    /**
     * 删除公告类型
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);

    /**
     *可用公告类型列表
     * @return
     *
     */
    ApiResp<List<NoteTypeEntity>> typeList();

    /**
     * 已经被使用的公告类型
     * @return
     */
    ApiResp<List<NoteTypeEntity>> queryNoteType();
}
