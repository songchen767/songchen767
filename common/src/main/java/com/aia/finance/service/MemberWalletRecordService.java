package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.MemberWalletRecordEntity;
import com.aia.finance.req.MemberWalletRecordPageReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员钱包记录 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MemberWalletRecordService extends IService<MemberWalletRecordEntity> {

    /**
     * 会员钱包记录分页查询
     * @return
     */
    ApiResp<PageResp<MemberWalletRecordEntity>> page(MemberWalletRecordPageReq memberWalletRecordPageReq);
}
