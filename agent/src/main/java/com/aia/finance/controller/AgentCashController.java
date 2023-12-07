package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.AgentCashEntity;
import com.aia.finance.req.AgentCashEditReq;
import com.aia.finance.req.ChannelBindCashReq;
import com.aia.finance.service.AgentCashService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 代理提现管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/agentCash")
@Api(tags="代理积分管理")
public class AgentCashController {

    @Autowired
    private AgentCashService agentCashService;

    @ApiOperation("代理积分信息获取")
    @GetMapping("/getCash/{channeId}")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash:query')")
    @ApiImplicitParam(name = "channeId",value = "channeId",required = true)
    public ApiResp<AgentCashEntity> getCash(@PathVariable("channeId") Long channeId ) {
        return agentCashService.getCash(channeId);
    }



    @PostMapping("/channelBindCash")
    @ApiOperation("积分新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash:create')")
    @LogOperate(operate = "添加", service = AgentCashService.class, type = DataOperateContants.ADD)
    public ApiResp<String> channelBindCash(@RequestBody @Valid ChannelBindCashReq channelBindCashReq){
        return agentCashService.channelBindCash(channelBindCashReq);
    }

    @PostMapping("/edit")
    @ApiOperation("积分修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash:edit')")
    @LogOperate(operate = "修改", service = AgentCashService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid AgentCashEditReq agentCashEditReq){
        return agentCashService.edit(agentCashEditReq);
    }




}
