package com.aia.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.BusinessException;
import com.aia.game.entity.GameEntity;
import com.aia.game.mapper.GameMapper;
import com.aia.game.req.GameAddReq;
import com.aia.game.req.GameEditReq;
import com.aia.game.req.GamePageReq;
import com.aia.game.req.GameUpdateStatusReq;
import com.aia.game.service.GameService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <p>
 * 游戏信息 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, GameEntity> implements GameService {


    /**
     * 根据游戏商id批量删除游戏
     */
    public ApiResp<String> delByGamerId(Long id) {
        LambdaQueryWrapper<GameEntity> lambdaQueryWrapper = new QueryWrapper<GameEntity>().lambda();
        lambdaQueryWrapper.eq(GameEntity::getGamerId, id);
        remove(lambdaQueryWrapper);
        return ApiResp.sucess();
    }


    /**
     * 根据游戏商id更新游戏状态
     */
    public ApiResp<String> updateStatusBygamerId(Long id, Integer status) {
        LambdaQueryWrapper<GameEntity> lambdaQueryWrapper = new QueryWrapper<GameEntity>().lambda();
        lambdaQueryWrapper.eq(GameEntity::getGamerId, id);
        List<GameEntity> list = list(lambdaQueryWrapper);
        List<GameEntity> newGameList = new ArrayList<>();
        for (GameEntity item : list) {
            item.setStatus(status);
            newGameList.add(item);
        }
        updateBatchById(newGameList);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<PageResp<GameEntity>> page(GamePageReq gamePageReq) {
        LambdaQueryWrapper<GameEntity> lambdaQuery = new QueryWrapper<GameEntity>().lambda();

        if (!StringUtils.isEmpty(gamePageReq.getName())) {
            lambdaQuery.like(GameEntity::getName, gamePageReq.getName());
        }
        if (!ObjectUtils.isEmpty(gamePageReq.getStatus())) {
            lambdaQuery.eq(GameEntity::getStatus, gamePageReq.getStatus());
        }
        if (!ObjectUtils.isEmpty(gamePageReq.getGamerId())) {
            lambdaQuery.eq(GameEntity::getGamerId, gamePageReq.getGamerId());
        }
        if (!ObjectUtils.isEmpty(gamePageReq.getType())) {
            lambdaQuery.eq(GameEntity::getType, gamePageReq.getType());
        }
        PageHelper.startPage(gamePageReq.getPageNo(), gamePageReq.getPageSize());
        Page<GameEntity> page = (Page<GameEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(GameAddReq gameAddReq) {
        LambdaQueryWrapper<GameEntity> lambdaQuery = new QueryWrapper<GameEntity>().lambda();
        lambdaQuery.eq(GameEntity::getName, gameAddReq.getName());
        GameEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("this name is repeat");
        }
        GameEntity entity = new GameEntity();
        BeanUtils.copyProperties(gameAddReq, entity);
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(GameEditReq gameEditReq) {
        LambdaQueryWrapper<GameEntity> lambdaQuery = new QueryWrapper<GameEntity>().lambda();
        lambdaQuery.eq(GameEntity::getName, gameEditReq.getName());
        lambdaQuery.ne(GameEntity::getId, gameEditReq.getGameId());
        GameEntity entity = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(entity)) {
            throw new BusinessException("this name is repeat");
        }
        GameEntity NewEntity = new GameEntity();
        BeanUtils.copyProperties(gameEditReq, NewEntity);
        updateById(NewEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(GameUpdateStatusReq gameUpdateStatusReq) {
        GameEntity entity = getById(gameUpdateStatusReq.getGameId());
        entity.setStatus(gameUpdateStatusReq.getStatus());
        updateById(entity);
        return ApiResp.sucess();
    }


}
