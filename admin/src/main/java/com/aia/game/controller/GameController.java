package com.aia.game.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.game.entity.GameEntity;
import com.aia.game.entity.GamerEntity;
import com.aia.game.req.*;
import com.aia.game.service.GamerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.aia.game.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 * 游戏信息 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/game/game")
@Api(tags = "游戏信息")
public class GameController {
    @Autowired
    private GameService gameService;

    @ApiOperation("游戏分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:game:query')")
    public ApiResp<PageResp<GameEntity>> page(GamePageReq gamePageReq) {
        return gameService.page(gamePageReq);
    }

    @PostMapping("/add")
    @ApiOperation("游戏新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:game:create')")
    @LogOperate(operate = "添加", service = GameService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid GameAddReq gameAddReq) {
        return gameService.add(gameAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("游戏修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:game:edit')")
    @LogOperate(operate = "修改", service = GameService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid GameEditReq gameEditReq) {
        return gameService.eidt(gameEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除游戏")
    @LogOperate(operate = "删除", service = GameService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:game:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return gameService.del(id);
    }

    /**
     * 权限启用/停用
     */
    @PostMapping("/updateStatus")
    @ApiOperation("游戏状态修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:game:edit')")
    @LogOperate(operate = "修改", service = GameService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid GameUpdateStatusReq gameUpdateStatusReq) {
        return gameService.updateStatus(gameUpdateStatusReq);
    }
}
