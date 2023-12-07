package com.aia.report.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.constans.DataOperateContants;
import com.aia.report.entity.PlatformReportDayEntity;
import com.aia.report.entity.PlatformReportMonthEntity;
import com.aia.report.req.ReportDayReq;
import com.aia.report.req.ReportMonthReq;
import com.aia.report.service.PlatformReportDayService;
import com.aia.report.service.PlatformReportMonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 平台月报表 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/report/platformReportMonth")
@Api(tags = "平台月报表")

public class PlatformReportMonthController {


    @Autowired
    private PlatformReportMonthService platformReportMonthService;

    //@HasLogin
    @ApiOperation("月亏盈统计")
    @PostMapping("/reportMonth")
    //@PreAuthorize("@ss.hasAnyPermissions('system:report_month:query')")
    @LogOperate(operate = "查询",service = PlatformReportMonthService.class,type = DataOperateContants.OTHER)
    public ApiResp<List<PlatformReportMonthEntity>> reportMonth(@RequestBody @Valid ReportMonthReq reportMonthReq){
        return platformReportMonthService.reportMonth(reportMonthReq);
    }



}
