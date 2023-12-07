package com.aia.user.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.resp.LoginResp;
import com.aia.user.entity.AgentEntity;
import com.aia.user.req.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 代理人信息 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentService extends IService<AgentEntity> {

    /**
     * 根据id查询代理
     *
     * @param id
     * @return
     */
    public AgentEntity checkParent(Long id);

    /**
     * 代理分页列表
     *
     * @param agentPageReq
     * @return
     */
    public ApiResp<PageResp<AgentEntity>> page(AgentPageReq agentPageReq);

    /**
     * 代理冻结/解冻
     *
     * @param agentStatusUpdateReq
     * @return
     */
    ApiResp<String> updateStatus(AgentStatusUpdateReq agentStatusUpdateReq);

    /**
     * 转移下级代理
     *
     * @param agentTransferReq
     * @return
     */
    ApiResp<String> transferAgent(AgentTransferReq agentTransferReq);

    /**
     * 代理注册
     * @param agentRegisteReq
     * @return
     */
    ApiResp<String> registe(AgentRegisteReq agentRegisteReq);

    /**
     * 下级代理分页列表
     * @param childAgentPageReq
     * @return
     */
    ApiResp<PageResp<AgentEntity>> childAgentPage(ChildAgentPageReq childAgentPageReq);

    /**
     * 新增代理
     * @param agentAddReq
     * @return
     */
    ApiResp<String> add(AgentAddReq agentAddReq);

    ApiResp<LoginResp> login(AgentLoginReq agentLoginReq);

    ApiResp<String> logOut(HttpServletRequest request);
}
