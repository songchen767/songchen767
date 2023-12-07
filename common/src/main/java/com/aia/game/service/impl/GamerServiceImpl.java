package com.aia.game.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.game.entity.GamerEntity;
import com.aia.game.mapper.GamerMapper;
import com.aia.game.req.GamerAddReq;
import com.aia.game.req.GamerEditReq;
import com.aia.game.req.GamerPageReq;
import com.aia.game.req.GamerStatusEditReq;
import com.aia.game.service.GameService;
import com.aia.game.service.GamerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 游戏商信息 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class GamerServiceImpl extends ServiceImpl<GamerMapper, GamerEntity> implements GamerService {


    @Autowired
    private GameService gameService;

    @Override
    public ApiResp<PageResp<GamerEntity>> page(GamerPageReq gamerPageReq) {

        LambdaQueryWrapper<GamerEntity> lambdaQuery = new QueryWrapper<GamerEntity>().lambda();

        if (!StringUtils.isEmpty(gamerPageReq.getName())) {
            lambdaQuery.like(GamerEntity::getName, gamerPageReq.getName());
        }
        if (!ObjectUtils.isEmpty(gamerPageReq.getStatus())) {
            lambdaQuery.eq(GamerEntity::getStatus, gamerPageReq.getStatus());
        }
        PageHelper.startPage(gamerPageReq.getPageNo(), gamerPageReq.getPageSize());
        Page<GamerEntity> page = (Page<GamerEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(GamerAddReq gamerAddReq) {
        LambdaQueryWrapper<GamerEntity> lambdaQuery = new QueryWrapper<GamerEntity>().lambda();
        lambdaQuery.eq(GamerEntity::getName, gamerAddReq.getName());
        GamerEntity gamerEntity = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(gamerEntity)) {
            throw new BusinessException("this param [name] is repeat");
        }
        GamerEntity newGamerEntity = new GamerEntity();
        BeanUtils.copyProperties(gamerAddReq, newGamerEntity);
        save(newGamerEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(GamerEditReq gamerEditReq) {
        GamerEntity gamerEntity = getById(gamerEditReq.getGamerId());
        if (ObjectUtils.isEmpty(gamerEntity)) {
            throw new BusinessException("data not exist");
        }
        LambdaQueryWrapper<GamerEntity> lambdaQuery = new QueryWrapper<GamerEntity>().lambda();
        lambdaQuery.eq(GamerEntity::getName, gamerEditReq.getName());
        lambdaQuery.ne(GamerEntity::getId, gamerEditReq.getGamerId());
        GamerEntity one = getOne(lambdaQuery);
        if (!ObjectUtils.isEmpty(one)) {
            throw new BusinessException("this param [name] is repeat");
        }
        BeanUtils.copyProperties(gamerEditReq, gamerEntity);
        updateById(gamerEntity);
        return ApiResp.sucess();
    }

    @Override
    @Transactional
    public ApiResp<String> del(Long id) {
        removeById(id);
        //同时删除游戏 todo
        gameService.delByGamerId(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(GamerStatusEditReq gamerStatusEditReq) {
        GamerEntity gamerEntity = getById(gamerStatusEditReq.getGamerId());
        if (ObjectUtils.isEmpty(gamerEntity)) {
            throw new BusinessException("data not exist");
        }
        GamerEntity gamer = new GamerEntity();
        BeanUtils.copyProperties(gamerEntity, gamer);
        gamer.setStatus(gamerStatusEditReq.getStatus());
        updateById(gamer);
        //同时更新游戏状态
        if (StatusEnum.OFF.getValue().equals(gamerStatusEditReq.getStatus())) {
            gameService.updateStatusBygamerId(gamerStatusEditReq.getGamerId(), gamerStatusEditReq.getStatus());
        }
        return ApiResp.sucess();
    }
}
