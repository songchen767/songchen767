package com.aia.system.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.*;
import com.aia.system.service.AuthorityService;
import com.aia.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 * 后台角色 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("角色分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:role:query')")
    public ApiResp<PageResp<RoleEntity>> page(RolePageReq rolePageReq) {
        return roleService.page(rolePageReq);
    }

    @PostMapping("/add")
    @ApiOperation("角色新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:role:create')")
    @LogOperate(operate = "添加", service = RoleService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid RoleAddReq roleAddReq) {
        return roleService.add(roleAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("角色修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:role:edit')")
    @LogOperate(operate = "修改", service = RoleService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid RoleEditReq roleEditReq) {
        return roleService.eidt(roleEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除角色")
    @LogOperate(operate = "删除", service = RoleService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:role:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return roleService.del(id);
    }

    /**
     * 权限启用/停用
     */
    @PostMapping("/updateStatus")
    @ApiOperation("角色状态修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:edit')")
    @LogOperate(operate = "修改", service = RoleService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid RoleStatusEditReq roleStatusEditReq) {
        return roleService.updateStatus(roleStatusEditReq);
    }


}
