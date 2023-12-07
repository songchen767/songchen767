package com.aia.user.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.user.entity.AgentEntity;
import com.aia.user.req.AgentPageReq;
import com.aia.user.req.AgentStatusUpdateReq;
import com.aia.user.req.AgentTransferReq;
import com.aia.user.service.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //@HasLogin
    @ApiOperation("代理分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:query')")
    @LogOperate(operate = "查询",service = AgentService.class,type = DataOperateContants.OTHER)
    public ApiResp<PageResp<AgentEntity>> page(AgentPageReq agentPageReq){
        return agentService.page(agentPageReq);
    }

    //@HasLogin
    @ApiOperation("代理冻结/解冻")
    @PostMapping("/updateStatus")
    @LogOperate(operate = "代理冻结/解冻",service = AgentService.class,type = DataOperateContants.EDIT)
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:edit')")
    public ApiResp<String> updateStatus(@RequestBody @Valid AgentStatusUpdateReq agentStatusUpdateReq){
        return agentService.updateStatus(agentStatusUpdateReq);
    }

    //@HasLogin
    @ApiOperation("转移下级代理")
    @PostMapping("/transferAgent")
    @LogOperate(operate = "转移下级代理",service = AgentService.class,type = DataOperateContants.EDIT)
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent:edit')")
    public ApiResp<String> transferAgent(@RequestBody @Valid AgentTransferReq agentTransferReq){
        return agentService.transferAgent(agentTransferReq);
    }

}
