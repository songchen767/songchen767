package com.aia.report.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.report.entity.AgentReportDayEntity;
import com.aia.report.req.AgentReportDayReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代理日报表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentReportDayService extends IService<AgentReportDayEntity> {

    /**
     * 根据所选日期区间查询当前代理日报表
     * @param agentReportDayReq
     * @return
     */
    public ApiResp<PageResp<AgentReportDayEntity>> page(AgentReportDayReq agentReportDayReq);

}
