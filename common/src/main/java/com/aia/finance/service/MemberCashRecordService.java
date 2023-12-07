package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.MemberCashRecordEntity;
import com.aia.finance.req.MemberCashRecordPageReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员提现记录 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MemberCashRecordService extends IService<MemberCashRecordEntity> {

    /**
     * 会员提现记录分页查询
     * @param memberCashRecordPageReq
     * @return
     */
    public ApiResp<PageResp<MemberCashRecordEntity>> page(MemberCashRecordPageReq memberCashRecordPageReq);

}
