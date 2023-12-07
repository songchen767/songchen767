package com.aia.system.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.system.entity.AuthorityEntity;
import com.aia.system.req.AuthorithEditReq;
import com.aia.system.req.AuthorityAddReq;
import com.aia.system.req.AuthorityPageReq;
import com.aia.system.req.AuthorityStatusEditReq;
import com.aia.system.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 后台权限 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/sys/authority")
@Api(tags = "后台权限")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @ApiOperation("权限分页列表")
    @GetMapping("/page")
    public ApiResp<PageResp<AuthorityEntity>> page(AuthorityPageReq authorityPageReq) {
        return authorityService.page(authorityPageReq);
    }

    @PostMapping("/add")
    @ApiOperation("权限新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:create')")
    @LogOperate(operate = "添加", service = AuthorityService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid AuthorityAddReq authorityAddReq) {
        return authorityService.add(authorityAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("权限修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:edit')")
    @LogOperate(operate = "修改", service = AuthorityService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid AuthorithEditReq authorithEditReq) {
        return authorityService.eidt(authorithEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除权限")
    @LogOperate(operate = "删除", service = AuthorityService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return authorityService.del(id);
    }


    /**
     * 权限启用/停用
     */
    @PostMapping("/updateStatus")
    @ApiOperation("权限状态修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:auth:edit')")
    @LogOperate(operate = "修改", service = AuthorityService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid AuthorityStatusEditReq authorityStatusEditReq) {
        return authorityService.updateStatus(authorityStatusEditReq);
    }


}
