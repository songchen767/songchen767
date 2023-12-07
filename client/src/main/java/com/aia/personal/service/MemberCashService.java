package com.aia.personal.service;

import com.aia.base.resp.ApiResp;
import com.aia.finance.req.QueryReq;
import com.aia.finance.req.WithdrawalsReq;
import com.aia.finance.res.CashInfoRes;
import com.aia.personal.entity.MemberCashEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员提现管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MemberCashService extends IService<MemberCashEntity> {


    /**
     * 提现
     * @param withdrawalsReq
     */
    public ApiResp<String> withdrawals(WithdrawalsReq withdrawalsReq);

    /**
     * 积分信息获取
     * @param queryReq
     * @return
     */
    ApiResp<List<CashInfoRes>> info(QueryReq queryReq);
}
