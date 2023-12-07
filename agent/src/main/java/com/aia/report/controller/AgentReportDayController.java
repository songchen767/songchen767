package com.aia.report.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.report.entity.AgentReportDayEntity;
import com.aia.report.req.AgentReportDayReq;
import com.aia.report.service.AgentReportDayService;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.MemberForAgentPageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 代理日报表 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/report/agentReportDay")
@Api(tags="代理日报表")
public class AgentReportDayController {

    @Autowired
    private AgentReportDayService agentReportDayService;

    @HasLogin
    @ApiOperation("代理日报表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_report_day:query')")
    public ApiResp<PageResp<AgentReportDayEntity>> page(AgentReportDayReq agentReportDayReq) {
        return agentReportDayService.page(agentReportDayReq);
    }


}
