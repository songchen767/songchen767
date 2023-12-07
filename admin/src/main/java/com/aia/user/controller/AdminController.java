package com.aia.user.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.resp.LoginResp;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.RoleAddReq;
import com.aia.system.req.RoleEditReq;
import com.aia.system.req.RolePageReq;
import com.aia.system.service.RoleService;
import com.aia.user.entity.AdminEntity;
import com.aia.user.req.*;
import com.aia.user.service.AdminService;
import com.aia.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 后台管理员 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/sys/admin")
@Api(tags = "后台管理用户模块")
public class AdminController {


    @Autowired
    private AdminService adminService;


    //@HasLogin
    @ApiOperation("用户分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:user:query')")
    @LogOperate(operate = "查询",service = AdminService.class,type = DataOperateContants.OTHER)
    public ApiResp<PageResp<AdminEntity>> page(AdminPageReq adminPageReq){
        return adminService.page(adminPageReq);
    }

    //@HasLogin
    @PostMapping("/add")
    @ApiOperation("用户新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:user:create')")
    @LogOperate(operate = "添加",service = AdminService.class,type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid UserAddReq userAddReq){
        return adminService.add(userAddReq);
    }

    //@HasLogin
    @PostMapping("/edit")
    @ApiOperation("用户修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:user:edit')")
    @LogOperate(operate = "修改",service = AdminService.class,type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid UserEditReq userEditReq){
        return adminService.eidt(userEditReq);
    }

    //@HasLogin
    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除用户")
    @LogOperate(operate = "删除",service = AdminService.class,type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id",value = "ID",required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:user:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return adminService.del(id);
    }

    //@HasLogin
    @PostMapping("/updateStatus")
    @ApiOperation(value="更新用户状态")
    @LogOperate(operate = "更新用户状态",service = AdminService.class,type = DataOperateContants.EDIT)
    //@PreAuthorize("@ss.hasAnyPermissions('system:user:edit')")
    public ApiResp<String> updateStatus(@RequestBody @Valid UserUpdateStatusReq userUpdateStatusReq){
        return adminService.updateStatus(userUpdateStatusReq);
    }

    @PostMapping("/login")
    @ApiOperation(value="用户登录")
    public ApiResp<LoginResp> login(@RequestBody @Valid MemberLoginReq memberLoginReq){
        return adminService.login(memberLoginReq);
    }

    @GetMapping("/logOut")
    @ApiOperation(value="用户登出")
    @LogOperate(operate = "登出",service = AdminService.class,type = DataOperateContants.OTHER)
    public ApiResp<String> logOut(HttpServletRequest request){
        return adminService.logOut(request);
    }


}
