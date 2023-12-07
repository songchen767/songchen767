package com.aia.scheduled;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aia.enums.RedisKeyEnums;
import com.aia.game.entity.GamerEntity;
import com.aia.game.service.GamerService;
import com.aia.game.service.KmApiService;
import com.aia.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/** 
 * 游戏商投注历史获取定时器
* @author yangxy
* @version 创建时间：2023年11月28日 下午12:04:20 
*/
@Slf4j
@Component
public class BetHistoryScheduled {
	@Autowired
	private KmApiService apiService;
	@Autowired
	private GamerService gamerService;
	@Autowired
	private RedisUtils redisUtils;
	
	public void run() {
		Map<Object, Object> map = Maps.newConcurrentMap();
		if(redisUtils.hasKey(RedisKeyEnums.GAMER_CAHE.key)) {
			map = redisUtils.hmget(RedisKeyEnums.GAMER_CAHE.key);
		}else {
			List<GamerEntity> list = gamerService.list(new QueryWrapper<GamerEntity>().lambda().eq(GamerEntity::getStatus, 1));
			new Thread() {
				public void run() {
					for(GamerEntity gamerEntity : list) {
						redisUtils.hset(RedisKeyEnums.GAMER_CAHE.key, gamerEntity.getId().toString(), gamerEntity);
					}
				}
			}.start();
			for(GamerEntity gamerEntity : list) {
				map.put(gamerEntity.getId().toString(), gamerEntity);
			}
		}
		
		for(Object key : map.keySet()) {
			GamerEntity gamerEntity = (GamerEntity) map.get(key);
			if("KM".equals(gamerEntity.getCode())){
				LocalDateTime now = LocalDateTime.now();
				String nowStr = now.toString().substring(0,now.toString().lastIndexOf("."));
				LocalDateTime afterFive = LocalDateTime.now().plusMinutes(5);
				String afterFiveStr = afterFive.toString().substring(0,afterFive.toString().lastIndexOf("."));
				apiService.getBetHistory(gamerEntity, nowStr, afterFiveStr);
			}
		}
	}
	
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(5);
		System.out.println(now.toString().substring(0,now.toString().lastIndexOf(".")));
		System.out.println(plusMinutes.toString().substring(0,plusMinutes.toString().lastIndexOf(".")));
	}
}
