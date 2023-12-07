package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.req.AgentCashPageReq;
import com.aia.finance.req.CashChannelPageReq;
import com.aia.finance.service.AgentCashRecordService;
import com.aia.finance.service.CashChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 代理提现记录 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/agentCashRecord")
@Api(tags = "代理积分模块")
public class AgentCashRecordController {

    @Autowired
    private AgentCashRecordService agentCashRecordService;

    @ApiOperation("代理积分分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash:query')")
    @LogOperate(operate = "查询", service = AgentCashRecordService.class, type = DataOperateContants.OTHER)
    public ApiResp<PageResp<AgentCashRecordEntity>> page(AgentCashPageReq agentCashPageReq) {
        return agentCashRecordService.page(agentCashPageReq);
    }



}
