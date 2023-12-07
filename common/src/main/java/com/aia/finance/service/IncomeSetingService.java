package com.aia.finance.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.IncomeSetingEntity;
import com.aia.finance.req.IncomeSettingAddReq;
import com.aia.finance.req.IncomeSettingEditReq;
import com.aia.finance.req.IncomeSettingPageReq;
import com.aia.finance.req.IncomeSettingUpdateStatusReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 充值配置管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface IncomeSetingService extends IService<IncomeSetingEntity> {

    /**
     * 根据渠道id更新配置启/停状态
     */
    public ApiResp<String> updateStatusByChannelId(Long channelId,Integer status);

    /**
     * 充值配置分页查询
     */
    public ApiResp<PageResp<IncomeSetingEntity>> page(IncomeSettingPageReq incomeSettingPageReq);

    /**
     * 新增充值配置
     * @param incomeSettingAddReq
     * @return
     */
    ApiResp<String> add(IncomeSettingAddReq incomeSettingAddReq);

    /**
     * 修改充值配置
     * @param incomeSettingEditReq
     * @return
     */
    ApiResp<String> eidt(IncomeSettingEditReq incomeSettingEditReq);

    /**
     * 删除充值配置
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);

    /**
     * 根据充值渠道查询充值配置列表
     * @param channelId
     * @return
     */
    ApiResp<List<IncomeSetingEntity>> queryByChannelId(Long channelId);

    /**
     * 根据主键id更新新配置启/停状态
     * @param incomeSettingUpdateStatusReq
     * @return
     */
    ApiResp<String> updateStatus(IncomeSettingUpdateStatusReq incomeSettingUpdateStatusReq);
}
