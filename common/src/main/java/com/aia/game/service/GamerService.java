package com.aia.game.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GamerEntity;
import com.aia.game.req.GamerAddReq;
import com.aia.game.req.GamerEditReq;
import com.aia.game.req.GamerPageReq;
import com.aia.game.req.GamerStatusEditReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 游戏商信息 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface GamerService extends IService<GamerEntity> {

    /**
     * 游戏商分页查询
     */
    public ApiResp<PageResp<GamerEntity>> page(GamerPageReq gamerPageReq);

    /**
     * 游戏商新增
     *
     * @param roleAddReq
     * @return
     */
    ApiResp<String> add(GamerAddReq roleAddReq);

    /**
     * 游戏商更新状态
     *
     * @param roleStatusEditReq
     * @return
     */
    ApiResp<String> updateStatus(GamerStatusEditReq roleStatusEditReq);

    /**
     * 游戏商修改
     *
     * @param roleEditReq
     * @return
     */
    ApiResp<String> eidt(GamerEditReq roleEditReq);

    /**
     * 游戏商删除
     *
     * @param id
     * @return
     */
    ApiResp<String> del(Long id);
}
