package com.aia.user.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.AgentCashEntity;
import com.aia.resp.LoginResp;
import com.aia.user.entity.AgentEntity;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.*;
import com.aia.user.service.AgentService;
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
 * 代理人信息 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/user/agent")
@Api(tags="代理人信息")
public class AgentController {


    @Autowired
    private AgentService agentService;

    @ApiOperation("代理注册/会员推广地址新增")
    @PostMapping("/registe")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:create')")
    public ApiResp<String> registe(@RequestBody @Valid AgentRegisteReq agentRegisteReq) {
        return agentService.registe(agentRegisteReq);
    }

    @ApiOperation("下级代理分页列表")
    @GetMapping("/childAgentPage")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:query')")
    public ApiResp<PageResp<AgentEntity>> childAgentPage(@RequestBody @Valid ChildAgentPageReq childAgentPageReq) {
        return agentService.childAgentPage(childAgentPageReq);
    }

    @ApiOperation("新增代理")
    @PostMapping("/add")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:create')")
    public ApiResp<String> add(@RequestBody @Valid AgentAddReq agentAddReq) {
        return agentService.add(agentAddReq);
    }

    @PostMapping("/login")
    @ApiOperation(value="用户登录")
    @LogOperate(operate = "登录",service = AgentService.class,type = DataOperateContants.OTHER)
    public ApiResp<LoginResp> login(@RequestBody @Valid AgentLoginReq agentLoginReq){
        return agentService.login(agentLoginReq);
    }

    @GetMapping("/logOut")
    @ApiOperation(value="用户登出")
    @LogOperate(operate = "登出",service = AgentService.class,type = DataOperateContants.OTHER)
    public ApiResp<String> logOut(HttpServletRequest request){
        return agentService.logOut(request);
    }


}
