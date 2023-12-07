package com.aia.msg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.msg.entity.NoteEntity;
import com.aia.msg.req.NoteAddReq;
import com.aia.msg.req.NoteEditReq;
import com.aia.msg.req.NotePageReq;
import com.aia.msg.req.NoteStatusEditReq;
import com.aia.msg.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 公告管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/msg/note")
@Api(tags="公告模块")
public class NoteController {

    @Autowired
    private NoteService noteService;

    //@HasLogin
    @ApiOperation("公告分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note:query')")
    public ApiResp<PageResp<NoteEntity>> page(NotePageReq noteTypePageReq) {
        return noteService.page(noteTypePageReq);
    }

    //@HasLogin
    @PostMapping("/add")
    @ApiOperation("公告新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note:create')")
    @LogOperate(operate = "添加", service = NoteService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid NoteAddReq noteAddReq) {
        return noteService.add(noteAddReq);
    }

    //@HasLogin
    @PostMapping("/edit")
    @ApiOperation("公告修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note:edit')")
    @LogOperate(operate = "修改", service = NoteService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid NoteEditReq noteEditReq) {
        return noteService.eidt(noteEditReq);
    }

    //@HasLogin
    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除公告")
    @LogOperate(operate = "删除", service = NoteService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:note:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return noteService.del(id);
    }

    /**
     * 权限启用/停用
     */
    //@HasLogin
    @PostMapping("/updateStatus")
    @ApiOperation("公告状态修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note:edit')")
    @LogOperate(operate = "修改", service = NoteService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid NoteStatusEditReq noteStatusEditReq) {
        return noteService.updateStatus(noteStatusEditReq);
    }

}
