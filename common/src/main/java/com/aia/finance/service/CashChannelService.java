package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.req.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 提现渠道管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface CashChannelService extends IService<CashChannelEntity> {

    ApiResp<PageResp<CashChannelEntity>> page(CashChannelPageReq cashChannelPageReq);

    /**
     * 新增积分渠道
     *
     * @param cashChannelAddReq
     * @return
     */
    ApiResp<String> add(CashChannelAddReq cashChannelAddReq);

    /**
     * 修改积分渠道
     *
     * @param cashChannelEditReq
     * @return
     */
    ApiResp<String> eidt(CashChannelEditReq cashChannelEditReq);

    /**
     * 删除积分渠道
     *
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);

    /**
     * 更新渠道状态
     *
     * @param cashChannelStatusUpdateReq
     * @return
     */
    ApiResp<String> updateStatus(CashChannelStatusUpdateReq cashChannelStatusUpdateReq);

    /**
     * 积分渠道获取   有效的积分渠道
     * @return
     */
    ApiResp<List<CashChannelEntity>> getCashChannel();

    /**
     * 根据渠道id获取渠道信息
     */
    CashChannelEntity getCashChannelById(Long id);

    /**
     * 渠道绑定积分账号
     */
    ApiResp<String> channelBindcash(ChannelBindCashReq channelBindCashReq);


}
