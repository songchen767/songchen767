package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.req.IncomeChannelAddReq;
import com.aia.finance.req.IncomeChannelEditReq;
import com.aia.finance.req.IncomeChannelPageReq;
import com.aia.finance.req.IncomeChannelStatusUpdateReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 充值渠道管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface IncomeChannelService extends IService<IncomeChannelEntity> {

    /**
     * 充值渠道分页查询
     * @param incomeChannelPageReq
     * @return
     */
    public ApiResp<PageResp<IncomeChannelEntity>> page(IncomeChannelPageReq incomeChannelPageReq);

    /**
     * 充值渠道新增
     * @param incomeChannelAddReq
     * @return
     */
    ApiResp<String> add(IncomeChannelAddReq incomeChannelAddReq);

    /**
     * 充值渠道修改
     * @param incomeChannelEditReq
     * @return
     */
    ApiResp<String> eidt(IncomeChannelEditReq incomeChannelEditReq);

    /**
     * 充值渠道删除
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);

    /**
     * 启/停渠道
     * @param incomeChannelStatusUpdateReq
     * @return
     */
    ApiResp<String> updateStatus(IncomeChannelStatusUpdateReq incomeChannelStatusUpdateReq);

    /**
     * 可用渠道列表
     * @return
     */
    ApiResp<List<IncomeChannelEntity>> channelList();

}
