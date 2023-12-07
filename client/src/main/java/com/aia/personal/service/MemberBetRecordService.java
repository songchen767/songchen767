package com.aia.personal.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.req.WithdrawalsReq;
import com.aia.personal.entity.MemberBetRecordEntity;
import com.aia.personal.req.BetRecordPageReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员投注记录表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MemberBetRecordService extends IService<MemberBetRecordEntity> {


    /**
     * 分页查询记录
     */
    public ApiResp<PageResp<MemberBetRecordEntity>> page(BetRecordPageReq betRecordPage);



}
