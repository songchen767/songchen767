package com.aia.game.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.dto.LoginDto;
import com.aia.enums.RedisKeyEnums;
import com.aia.exception.ApiBussException;
import com.aia.finance.entity.MemberWalletEntity;
import com.aia.finance.service.MemberWalletService;
import com.aia.game.entity.GameEntity;
import com.aia.game.entity.GamerEntity;
import com.aia.game.req.CreditReq;
import com.aia.game.req.DeditReq;
import com.aia.game.req.EnterGameReq;
import com.aia.game.req.GameApiPageResp;
import com.aia.game.service.GameApiService;
import com.aia.game.service.GameService;
import com.aia.game.service.GamerService;
import com.aia.game.service.KmApiService;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午5:57:31 
*/
@Service
public class GameApiServiceImpl implements GameApiService {
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private GameService gameService;
	@Autowired
	private KmApiService kmApiService;
	@Autowired
	private GamerService gamerService;
	@Autowired
	private MemberWalletService memberWalletService;

	@Override
	public ApiResp<PageResp<GameEntity>> page(GameApiPageResp gameApiPageResp) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<GameEntity> lambda = new QueryWrapper<GameEntity>().lambda();
		if(!ObjectUtils.isEmpty(gameApiPageResp.getGamerProviderId())) {
			lambda.eq(GameEntity::getGamerId, gameApiPageResp.getGamerProviderId());
		}
		
		if(!ObjectUtils.isEmpty(gameApiPageResp.getStatus())) {
			lambda.eq(GameEntity::getStatus, gameApiPageResp.getStatus());
		}
		
		if(!ObjectUtils.isEmpty(gameApiPageResp.getType())) {
			lambda.eq(GameEntity::getType, gameApiPageResp.getType());
		}
		PageHelper.startPage(gameApiPageResp.getPageNo(), gameApiPageResp.getPageSize());
		Page<GameEntity> page = (Page<GameEntity>) gameService.list(lambda);
		return ApiResp.page(page);
	}

	@Override
	public ApiResp<String> enterGame(EnterGameReq enterGameReq) {
		// TODO Auto-generated method stub
		GamerEntity gamerEntity = null;
		if(redisUtils.hasKey(RedisKeyEnums.GAMER_CAHE.key)) {
			gamerEntity = (GamerEntity) redisUtils.hget(RedisKeyEnums.GAMER_CAHE.key, enterGameReq.getGamerId().toString());
		}else {
			gamerEntity = gamerService.getById(enterGameReq.getGamerId());
			if(ObjectUtils.isEmpty(gamerEntity)) {
				throw new ApiBussException("game merchant does not exist");
			}
			redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
		}
		if("KM".equals(gamerEntity.getCode())) {
			return kmApiService.enterGame(enterGameReq, gamerEntity);
		}
		throw new ApiBussException("game merchant does not exist");
	}

	@Override
	public ApiResp<Double> gamerbalance(Long gammerId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		GamerEntity gamerEntity = null;
		if(redisUtils.hasKey(RedisKeyEnums.GAMER_CAHE.key)) {
			gamerEntity = (GamerEntity) redisUtils.hget(RedisKeyEnums.GAMER_CAHE.key, gammerId.toString());
		}else {
			gamerEntity = gamerService.getById(gammerId);
			if(ObjectUtils.isEmpty(gamerEntity)) {
				throw new ApiBussException("game merchant does not exist");
			}
			redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
		}
		if("KM".equals(gamerEntity.getCode())) {
			return kmApiService.gamerbalance(gamerEntity);
		}
		throw new ApiBussException("game merchant does not exist");
	}

	@Override
	public ApiResp<String> credit(CreditReq creditReq) {
		// TODO Auto-generated method stub
		if(creditReq.getAmt() < 0) {
			throw new ApiBussException("the amount must be greater than 0");
		}
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		MemberWalletEntity memberWalletEntity = memberWalletService.getOne(new QueryWrapper<MemberWalletEntity>().lambda().eq(MemberWalletEntity::getMemberId, loginUser.getUserId()));
		if(ObjectUtils.isEmpty(memberWalletEntity)) {
			throw new ApiBussException("failed to obtain available balance");
		}
		if(memberWalletEntity.getBalance().doubleValue() < creditReq.getAmt().doubleValue()) {
			throw new ApiBussException("insufficient available balance");
		}
		
		GamerEntity gamerEntity = null;
		if(redisUtils.hasKey(RedisKeyEnums.GAMER_CAHE.key)) {
			gamerEntity = (GamerEntity) redisUtils.hget(RedisKeyEnums.GAMER_CAHE.key, creditReq.getGamerId().toString());
		}else {
			gamerEntity = gamerService.getById(creditReq.getGamerId());
			if(ObjectUtils.isEmpty(gamerEntity)) {
				throw new ApiBussException("game merchant does not exist");
			}
			redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
		}
		if("KM".equals(gamerEntity.getCode())) {
			return kmApiService.credit(gamerEntity, creditReq.getAmt());
		}
		throw new ApiBussException("game merchant does not exist");
	}

	@Override
	public ApiResp<String> debit(DeditReq deditReq) {
		// TODO Auto-generated method stub
		if(deditReq.getAmt() < 0) {
			throw new ApiBussException("the amount must be greater than 0");
		}
		GamerEntity gamerEntity = null;
		if(redisUtils.hasKey(RedisKeyEnums.GAMER_CAHE.key)) {
			gamerEntity = (GamerEntity) redisUtils.hget(RedisKeyEnums.GAMER_CAHE.key, deditReq.getGamerId().toString());
		}else {
			gamerEntity = gamerService.getById(deditReq.getGamerId());
			if(ObjectUtils.isEmpty(gamerEntity)) {
				throw new ApiBussException("game merchant does not exist");
			}
			redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
		}
		if("KM".equals(gamerEntity.getCode())) {
			return kmApiService.debit(gamerEntity, deditReq.getAmt());
		}
		throw new ApiBussException("game merchant does not exist");
	}

	
}
