package com.aia.msg.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.msg.entity.NoteEntity;
import com.aia.msg.req.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公告管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface NoteService extends IService<NoteEntity> {


    /**
     * 公告分页查询
     * @param notePageReq
     * @return
     */
    public ApiResp<PageResp<NoteEntity>> page(NotePageReq notePageReq);

    /**
     * 公告新增
     * @param noteAddReq
     * @return
     */
    ApiResp<String> add(NoteAddReq noteAddReq);

    /**
     * 公告修改
     * @param noteEditReq
     * @return
     */
    ApiResp<String> eidt(NoteEditReq noteEditReq);

    /**
     * 公告状态更新
     * @param noteStatusEditReq
     * @return
     */
    ApiResp<String> updateStatus(NoteStatusEditReq noteStatusEditReq);

    /**
     * 删除公告
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);

    /**
     * 已经发布的公告查询
     */
    List<NoteEntity> queryEffectiveNote();

    /**
     * 根据公告类型id查询公告
     */
    public ApiResp<PageResp<NoteEntity>> queryNoteByType(NoteListReq noteListReq);

    ApiResp<NoteEntity> queryNoteDetail(Long id);
}
