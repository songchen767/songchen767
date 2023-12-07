package com.aia.report.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.report.entity.PlatformReportDayEntity;
import com.aia.report.mapper.PlatformReportDayMapper;
import com.aia.report.req.ReportDayReq;
import com.aia.report.service.PlatformReportDayService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 平台日报表 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class PlatformReportDayServiceImpl extends ServiceImpl<PlatformReportDayMapper, PlatformReportDayEntity> implements PlatformReportDayService {

    @Override
    public ApiResp<List<PlatformReportDayEntity>> reportDay(ReportDayReq reportDayReq) {
        LambdaQueryWrapper<PlatformReportDayEntity> lambdaQuery = new QueryWrapper<PlatformReportDayEntity>().lambda();
        lambdaQuery.ge(PlatformReportDayEntity::getDay, reportDayReq.getStartDate());
        lambdaQuery.le(PlatformReportDayEntity::getDay, reportDayReq.getEndDate());
        List<PlatformReportDayEntity> list = list(lambdaQuery);
        return ApiResp.sucess(list);
    }
}
