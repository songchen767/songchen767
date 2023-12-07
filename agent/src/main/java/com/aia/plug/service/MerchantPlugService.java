package com.aia.plug.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.plug.req.MerchantPlugAddReq;
import com.aia.plug.req.MerchantPlugEditReq;
import com.aia.plug.req.MerchantPlugPageReq;
import com.aia.plug.entity.MerchantPlugEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 代理人推广管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MerchantPlugService extends IService<MerchantPlugEntity> {


    /**
     * 代理推广分页查询
     */
    public ApiResp<PageResp<MerchantPlugEntity>> page(MerchantPlugPageReq merchantPlugPageReq, HttpServletRequest request);

    /**
     * 推广新增
     *
     * @param merchantPlugAddReq
     * @return
     */
    public ApiResp<String> add(MerchantPlugAddReq merchantPlugAddReq);

    /**
     * 推广修改
     *
     * @param merchantPlugEditReq
     * @return
     */
    public ApiResp<String> eidt(MerchantPlugEditReq merchantPlugEditReq);

    /**
     * 推广删除
     * @param id
     * @return
     */
    public ApiResp<String> del(Long id);
}
