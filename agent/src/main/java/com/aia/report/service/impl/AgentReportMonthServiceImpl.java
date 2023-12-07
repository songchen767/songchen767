package com.aia.report.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.dto.LoginDto;
import com.aia.report.entity.AgentReportDayEntity;
import com.aia.report.entity.AgentReportMonthEntity;
import com.aia.report.mapper.AgentReportMonthMapper;
import com.aia.report.req.AgentReportDayReq;
import com.aia.report.req.AgentReportMonthReq;
import com.aia.report.service.AgentReportMonthService;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 代理月报表 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentReportMonthServiceImpl extends ServiceImpl<AgentReportMonthMapper, AgentReportMonthEntity> implements AgentReportMonthService {

    @Override
    public ApiResp<PageResp<AgentReportMonthEntity>> page(AgentReportMonthReq agentReportMonthReq) {

            LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
            LambdaQueryWrapper<AgentReportMonthEntity> lambdaQuery=new QueryWrapper<AgentReportMonthEntity>().lambda();
            if(!StringUtils.isEmpty(agentReportMonthReq.getStartMonth())){
                lambdaQuery.ge(AgentReportMonthEntity::getMonth,agentReportMonthReq.getStartMonth());
            }
            if(!StringUtils.isEmpty(agentReportMonthReq.getEndMonth())){
                lambdaQuery.le(AgentReportMonthEntity::getMonth,agentReportMonthReq.getEndMonth());
            }
            lambdaQuery.eq(AgentReportMonthEntity::getAgentId,loginUser.getUserId());
            PageHelper.startPage(agentReportMonthReq.getPageNo(), agentReportMonthReq.getPageSize());
            Page<AgentReportMonthEntity> page = (Page<AgentReportMonthEntity>) list(lambdaQuery);
            return ApiResp.page(page);

    }
}
