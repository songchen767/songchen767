package com.aia.report.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.report.entity.PlatformReportDayEntity;
import com.aia.report.entity.PlatformReportMonthEntity;
import com.aia.report.mapper.PlatformReportMonthMapper;
import com.aia.report.req.ReportMonthReq;
import com.aia.report.service.PlatformReportMonthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 平台月报表 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class PlatformReportMonthServiceImpl extends ServiceImpl<PlatformReportMonthMapper, PlatformReportMonthEntity> implements PlatformReportMonthService {

    @Override
    public ApiResp<List<PlatformReportMonthEntity>> reportMonth(ReportMonthReq reportMonthReq) {
        LambdaQueryWrapper<PlatformReportMonthEntity> lambdaQuery = new QueryWrapper<PlatformReportMonthEntity>().lambda();
        lambdaQuery.ge(PlatformReportMonthEntity::getMonth, reportMonthReq.getStartMonth());
        lambdaQuery.le(PlatformReportMonthEntity::getMonth, reportMonthReq.getEndMonth());
        List<PlatformReportMonthEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }
}
