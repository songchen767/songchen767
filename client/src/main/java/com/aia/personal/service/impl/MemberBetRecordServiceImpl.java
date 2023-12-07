package com.aia.personal.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GameEntity;
import com.aia.game.service.GameService;
import com.aia.personal.entity.MemberBetRecordEntity;
import com.aia.personal.mapper.MemberBetRecordMapper;
import com.aia.personal.req.BetRecordPageReq;
import com.aia.personal.service.MemberBetRecordService;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员投注记录表 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MemberBetRecordServiceImpl extends ServiceImpl<MemberBetRecordMapper, MemberBetRecordEntity> implements MemberBetRecordService {

    @Autowired
    private GameService gameService;


    @Override
    public ApiResp<PageResp<MemberBetRecordEntity>> page(BetRecordPageReq betRecordPage) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        LambdaQueryWrapper<MemberBetRecordEntity> lambdaQueryWrapper = new QueryWrapper<MemberBetRecordEntity>().lambda();
        lambdaQueryWrapper.eq(MemberBetRecordEntity::getMemberId, loginUserId);
        if (!StringUtils.isEmpty(betRecordPage.getGameType())) {
            LambdaQueryWrapper<GameEntity> queryWrapper = new QueryWrapper<GameEntity>().lambda();
            queryWrapper.eq(GameEntity::getType, betRecordPage.getGameType()).select(GameEntity::getId);
            List<GameEntity> list = gameService.list(queryWrapper);
            if (!CollectionUtils.isEmpty(list)) {
                List<Long> ids = new ArrayList<>();
                for (GameEntity item : list) {
                    ids.add(item.getId());
                }
                lambdaQueryWrapper.in(MemberBetRecordEntity::getGameId, ids);
            }
        }
        if (!ObjectUtils.isEmpty(betRecordPage.getStartTime())) {
            lambdaQueryWrapper.ge(MemberBetRecordEntity::getCreatedAt, betRecordPage.getStartTime());
        }
        if (!ObjectUtils.isEmpty(betRecordPage.getEndTime())) {
            lambdaQueryWrapper.le(MemberBetRecordEntity::getCreatedAt, betRecordPage.getEndTime());
        }
        if (!ObjectUtils.isEmpty(betRecordPage.getStatus())) {
            lambdaQueryWrapper.eq(MemberBetRecordEntity::getStatus, betRecordPage.getStatus());
        }
        PageHelper.startPage(betRecordPage.getPageNo(), betRecordPage.getPageSize());
        Page<MemberBetRecordEntity> page = (Page<MemberBetRecordEntity>) list(lambdaQueryWrapper);
        return ApiResp.page(page);
    }


}
