package com.aia.report.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.dto.LoginDto;
import com.aia.report.entity.AgentReportDayEntity;
import com.aia.report.mapper.AgentReportDayMapper;
import com.aia.report.req.AgentReportDayReq;
import com.aia.report.service.AgentReportDayService;
import com.aia.user.entity.MemberEntity;
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
 * 代理日报表 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentReportDayServiceImpl extends ServiceImpl<AgentReportDayMapper, AgentReportDayEntity> implements AgentReportDayService {

    @Override
    public ApiResp<PageResp<AgentReportDayEntity>> page(AgentReportDayReq agentReportDayReq) {
        LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
        LambdaQueryWrapper<AgentReportDayEntity> lambdaQuery=new QueryWrapper<AgentReportDayEntity>().lambda();
        if(!StringUtils.isEmpty(agentReportDayReq.getStartDate())){
            lambdaQuery.ge(AgentReportDayEntity::getDay,agentReportDayReq.getStartDate());
        }
        if(!StringUtils.isEmpty(agentReportDayReq.getEndDate())){
            lambdaQuery.le(AgentReportDayEntity::getDay,agentReportDayReq.getEndDate());
        }
        lambdaQuery.eq(AgentReportDayEntity::getAgentId,loginUser.getUserId());
        PageHelper.startPage(agentReportDayReq.getPageNo(), agentReportDayReq.getPageSize());
        Page<AgentReportDayEntity> page = (Page<AgentReportDayEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }
}
