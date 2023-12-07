package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.finance.entity.AgentCashEntity;
import com.aia.finance.req.AgentCashEditReq;
import com.aia.finance.req.ChannelBindCashReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代理提现管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentCashService extends IService<AgentCashEntity> {

    /**
     * 积分信息获取
     * @param channeId
     * @return
     */
    ApiResp<AgentCashEntity> getCash(Long channeId);

    /**
     * 积分绑定渠道
     * @param channelBindCashReq
     * @return
     */
    ApiResp<String> channelBindCash(ChannelBindCashReq channelBindCashReq);

    /**
     * 积分信息修改
     * @param agentCashEditReq
     * @return
     */
    ApiResp<String> edit(AgentCashEditReq agentCashEditReq);
}
