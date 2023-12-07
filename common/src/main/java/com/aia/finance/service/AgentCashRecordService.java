package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.AgentCashRecordEntity;
import com.aia.finance.req.AgentCashPageReq;
import com.aia.finance.req.AgentCashRecordAddReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代理提现记录 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentCashRecordService extends IService<AgentCashRecordEntity> {

    /**
     * 积分记录分页查询
     * @param agentCashRequest
     * @return
     */
    public ApiResp<PageResp<AgentCashRecordEntity>> page(AgentCashPageReq agentCashRequest);


    ApiResp<String> add(AgentCashRecordAddReq agentCashRecordAddReq);
}
