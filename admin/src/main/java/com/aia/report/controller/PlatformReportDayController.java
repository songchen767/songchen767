package com.aia.report.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.req.AgentCashPageReq;
import com.aia.finance.service.AgentCashRecordService;
import com.aia.report.entity.PlatformReportDayEntity;
import com.aia.report.req.ReportDayReq;
import com.aia.report.service.PlatformReportDayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 平台日报表 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/report/platformReportDay")
@Api(tags = "平台日报表")
public class PlatformReportDayController {

    @Autowired
    private PlatformReportDayService platformReportDayService;

    //@HasLogin
    @ApiOperation("日亏盈统计")
    @PostMapping("/reportDay")
    //@PreAuthorize("@ss.hasAnyPermissions('system:report_day:query')")
    @LogOperate(operate = "查询", service = PlatformReportDayService.class, type = DataOperateContants.OTHER)
    public ApiResp<List<PlatformReportDayEntity>> reportDay(@RequestBody @Valid ReportDayReq reportDayReq) {
        return platformReportDayService.reportDay(reportDayReq);
    }

}
