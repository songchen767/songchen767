package com.aia.system.controller;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.RoleAuthAddReq;
import com.aia.system.req.RolePageReq;
import com.aia.system.service.RoleAuthorityLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 16:53
 */
@RestController
@RequestMapping("/roleAuthority")
@Api(tags = "角色分配权限")
public class RoleAuthorityLinkController {

    @Autowired
    private RoleAuthorityLinkService roleAuthorityLinkService;

    @ApiOperation("角色分配权限")
    @PostMapping("/assignment")
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:create')")
    public ApiResp<String> assignment(@RequestBody @Valid RoleAuthAddReq roleAuthAddReq) {
        return roleAuthorityLinkService.assignment(roleAuthAddReq);
    }
}
