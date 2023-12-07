package com.aia.report.service;

import com.aia.base.resp.ApiResp;
import com.aia.report.entity.PlatformReportMonthEntity;
import com.aia.report.req.ReportMonthReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台月报表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface PlatformReportMonthService extends IService<PlatformReportMonthEntity> {

    /**
     * 月亏盈统计
     * @Auth chentao
     * @param reportMonthReq
     * @return
     */
    ApiResp<List<PlatformReportMonthEntity>> reportMonth(ReportMonthReq reportMonthReq);
}
