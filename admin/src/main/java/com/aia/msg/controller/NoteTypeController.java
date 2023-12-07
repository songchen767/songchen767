package com.aia.msg.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.msg.entity.NoteTypeEntity;
import com.aia.msg.req.NoteTypeAddReq;
import com.aia.msg.req.NoteTypeEditReq;
import com.aia.msg.req.NoteTypePageReq;
import com.aia.msg.req.NoteTypeStatusEditReq;
import com.aia.msg.service.NoteTypeService;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.RoleAddReq;
import com.aia.system.req.RoleEditReq;
import com.aia.system.req.RolePageReq;
import com.aia.system.req.RoleStatusEditReq;
import com.aia.system.service.AuthorityService;
import com.aia.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 公告类型管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/msg/noteType")
@Api(tags="公告类型")
public class NoteTypeController {

    @Autowired
    private NoteTypeService noteTypeService;

    //@HasLogin
    @ApiOperation("公告类型分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note_type:query')")
    public ApiResp<PageResp<NoteTypeEntity>> page(NoteTypePageReq noteTypePageReq) {
        return noteTypeService.page(noteTypePageReq);
    }

    //@HasLogin
    @PostMapping("/add")
    @ApiOperation("公告类型新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note_type:create')")
    @LogOperate(operate = "添加", service = NoteTypeService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid NoteTypeAddReq noteTypeAddReq) {
        return noteTypeService.add(noteTypeAddReq);
    }

    //@HasLogin
    @PostMapping("/edit")
    @ApiOperation("公告类型修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note_type:edit')")
    @LogOperate(operate = "修改", service = NoteTypeService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid NoteTypeEditReq noteTypeEditReq) {
        return noteTypeService.eidt(noteTypeEditReq);
    }

    //@HasLogin
    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除公告类型")
    @LogOperate(operate = "删除", service = NoteTypeService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:note_type:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return noteTypeService.del(id);
    }

    /**
     * 启用/停用
     */
    //@HasLogin
    @PostMapping("/updateStatus")
    @ApiOperation("公告类型状态修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:note_type:edit')")
    @LogOperate(operate = "修改", service = NoteTypeService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid NoteTypeStatusEditReq noteTypeStatusEditReq) {
        return noteTypeService.updateStatus(noteTypeStatusEditReq);
    }

    /**
     * 公告类型列表
     */
    @PostMapping("/typeList")
    @ApiOperation("公告类型查询")
    @LogOperate(operate = "查询", service = NoteTypeService.class, type = DataOperateContants.OTHER)
    public ApiResp<List<NoteTypeEntity>> typeList(){
        return noteTypeService.typeList();
    }
}
