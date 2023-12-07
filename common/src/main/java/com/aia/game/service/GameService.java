package com.aia.game.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GameEntity;
import com.aia.game.req.GameAddReq;
import com.aia.game.req.GameEditReq;
import com.aia.game.req.GamePageReq;
import com.aia.game.req.GameUpdateStatusReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 游戏信息 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface GameService extends IService<GameEntity> {

    /**
     * 根据游戏商id批量删除游戏
     */
    public ApiResp<String> delByGamerId(Long id);


    /**
     * 根据游戏商id更新游戏状态
     */
    public ApiResp<String> updateStatusBygamerId(Long id, Integer status);


    /**
     * 游戏分页查询
     */
    public ApiResp<PageResp<GameEntity>> page(GamePageReq gamePageReq);

    /**
     * 新增
     */
    public ApiResp<String> add(GameAddReq gameAddReq);

    /**
     * 修改
     *
     * @return
     */
    public ApiResp<String> eidt(GameEditReq gameEditReq);

    /**
     * 删除
     *
     * @return
     */
    public ApiResp<String> del(Long id);

    /**
     * 更新状态
     *
     * @return
     */
    public ApiResp<String> updateStatus(GameUpdateStatusReq gameUpdateStatusReq);


}
