package com.aia.report.service;

import com.aia.base.resp.ApiResp;
import com.aia.report.entity.PlatformReportDayEntity;
import com.aia.report.req.ReportDayReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台日报表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface PlatformReportDayService extends IService<PlatformReportDayEntity> {

    /**
     * 根据日期查询日报表/日亏盈统计
     * @Author chentao
     * @param reportDayReq
     * @return
     */
    ApiResp<List<PlatformReportDayEntity>> reportDay(ReportDayReq reportDayReq);
}
