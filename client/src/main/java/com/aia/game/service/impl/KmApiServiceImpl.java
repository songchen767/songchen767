package com.aia.game.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aia.amq.dto.WalletChangeDto;
import com.aia.base.resp.ApiResp;
import com.aia.dto.LoginDto;
import com.aia.enums.AmqEnums;
import com.aia.enums.RedisKeyEnums;
import com.aia.game.entity.GamerEntity;
import com.aia.game.req.EnterGameReq;
import com.aia.game.service.KmApiService;
import com.aia.util.AmqUtils;
import com.aia.util.HttpUtil;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/** 
* @author yangxy
* @version 创建时间：2023年11月28日 上午11:32:31 
*/
@Service
public class KmApiServiceImpl implements KmApiService {
	@Autowired
	private AmqUtils amqUtils;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private HttpUtil httpUtil;
	
	@Override
	public ApiResp<String> enterGame(EnterGameReq enterGameReq,GamerEntity gamerEntity) {
		// TODO Auto-generated method stub
		Map<String,String> headerMap = getHeaderMap(gamerEntity);
		//获取token
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		Map<String,Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("ipaddress", "192.168.1.102");
		paramMap.put("username", loginUser.getUserName());
		paramMap.put("userid", loginUser.getUserId()+"");
		paramMap.put("lang", "vi-VN");
		paramMap.put("cur", "VND");
		paramMap.put("betlimitid", 1);
		paramMap.put("istestplayer", true);
		paramMap.put("platformtype", 0);
		String jsonString = JSON.toJSONString(paramMap);
		ResponseEntity<String> doJsonPost = httpUtil.doJsonPost(gamerEntity.getApiBaseUrl()+"/api/player/authorize", jsonString, headerMap);
		JSONObject parseObject = JSON.parseObject(doJsonPost.getBody());
		String authtoken = parseObject.getString("authtoken");
		
		String url = gamerEntity.getPlayBaseUrl()+"/gamelauncher?gpcode="+enterGameReq.getProviderCode()+"&gcode="+enterGameReq.getCode()+"&token="+authtoken;
		return ApiResp.sucess(url);
	}

	@Override
	public ApiResp<Double> gamerbalance(GamerEntity gamerEntity) {
		// TODO Auto-generated method stub
		redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
		Map<String,String> headerMap = getHeaderMap(gamerEntity);
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		ResponseEntity<String> doGet = httpUtil.doGet(gamerEntity.getApiBaseUrl()+"/api/player/balance?userid="+loginUser.getUserId()+"&cur=VND", headerMap);
		JSONObject parseObject1 = JSON.parseObject(doGet.getBody());
		return ApiResp.sucess(parseObject1.getDouble("bal"));
	}

	
	/**
	 * 组装请求头
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午9:55:22 
	* @param gamerEntity 游戏商对象
	* @return
	 */
	private Map<String,String> getHeaderMap(GamerEntity gamerEntity){
		Map<String,String> headerMap = Maps.newConcurrentMap();
		headerMap.put("X-QM-ClientId", gamerEntity.getClientId());
		headerMap.put("X-QM-ClientSecret", gamerEntity.getClientSecret());
		return headerMap;
	}

	@Override
	public ApiResp<String> credit(GamerEntity gamerEntity,Double amt) {
		// TODO Auto-generated method stub
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		Map<String, String> headerMap = getHeaderMap(gamerEntity);
		Map<String,Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("userid", loginUser.getUserId().toString());
		paramMap.put("amt", amt);
		paramMap.put("cur", "VND");
		paramMap.put("txid", UUID.randomUUID().toString());
		ResponseEntity<String> doJsonPost = httpUtil.doJsonPost(gamerEntity.getApiBaseUrl()+"/api/wallet/credit", JSON.toJSONString(paramMap), headerMap);
		JSONObject parseObject = JSON.parseObject(JSON.toJSONString(doJsonPost));
		if(parseObject.getIntValue("statusCodeValue") == 200) {
			WalletChangeDto walletChangeDto = new WalletChangeDto();
			walletChangeDto.setUserId(loginUser.getUserId());
			walletChangeDto.setUserType(0);//用户类型（0 会员，1代理）
			walletChangeDto.setAmt(amt);
			walletChangeDto.setRemark("转入KM游戏钱包");
			walletChangeDto.setType(3);//变动类型（1提现，2充值，3转出到游戏商，4从游戏商转入）
			amqUtils.sendMqMsg(AmqEnums.WALLET_CHANGE_AMQ.exchangeName, AmqEnums.WALLET_CHANGE_AMQ.routeKey, JSON.toJSONString(walletChangeDto));
		}
		return ApiResp.sucess();
	}

	@Override
	public ApiResp<String> debit(GamerEntity gamerEntity,Double amt) {
		// TODO Auto-generated method stub
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		Map<String, String> headerMap = getHeaderMap(gamerEntity);
		Map<String,Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("userid", loginUser.getUserId().toString());
		paramMap.put("amt", amt);
		paramMap.put("cur", "VND");
		paramMap.put("txid", UUID.randomUUID().toString());
		ResponseEntity<String> doJsonPost = httpUtil.doJsonPost(gamerEntity.getApiBaseUrl()+"/api/wallet/debit", JSON.toJSONString(paramMap), headerMap);
		JSONObject parseObject = JSON.parseObject(JSON.toJSONString(doJsonPost));
		if(parseObject.getIntValue("statusCodeValue") == 200) {
			WalletChangeDto walletChangeDto = new WalletChangeDto();
			walletChangeDto.setUserId(loginUser.getUserId());
			walletChangeDto.setUserType(0);//用户类型（0 会员，1代理）
			walletChangeDto.setAmt(amt);
			walletChangeDto.setRemark("从KM游戏钱包转出");
			walletChangeDto.setType(4);//变动类型（1提现，2充值，3转出到游戏商，4从游戏商转入）
			amqUtils.sendMqMsg(AmqEnums.WALLET_CHANGE_AMQ.exchangeName, AmqEnums.WALLET_CHANGE_AMQ.routeKey, JSON.toJSONString(walletChangeDto));
		}
		return ApiResp.sucess();
	}

	@Override
	public void getBetHistory(GamerEntity gamerEntity,String startTime,String endTime) {
		// TODO Auto-generated method stub
		Map<String, String> headerMap = getHeaderMap(gamerEntity);
		ResponseEntity<String> doGet = httpUtil.doGet(gamerEntity.getApiBaseUrl()+"/api/history/bets?startdate="+startTime+"&enddate="+endTime+"&issettled=true", headerMap);
		System.out.println(doGet);
	}
}
