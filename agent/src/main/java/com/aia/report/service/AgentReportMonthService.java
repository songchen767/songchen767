package com.aia.report.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.report.entity.AgentReportDayEntity;
import com.aia.report.entity.AgentReportMonthEntity;
import com.aia.report.req.AgentReportDayReq;
import com.aia.report.req.AgentReportMonthReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代理月报表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentReportMonthService extends IService<AgentReportMonthEntity> {

    /**
     * 根据所选日期区间查询当前代理月报表
     * @param agentReportMonthReq
     * @return
     */
    public ApiResp<PageResp<AgentReportMonthEntity>> page(AgentReportMonthReq agentReportMonthReq);


}
