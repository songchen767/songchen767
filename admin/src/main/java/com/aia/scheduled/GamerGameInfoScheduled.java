package com.aia.scheduled;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aia.game.entity.GameEntity;
import com.aia.game.entity.GamerEntity;
import com.aia.game.service.GameService;
import com.aia.game.service.GamerService;
import com.aia.scheduled.dto.KmGameDto;
import com.aia.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/** 
 * 定时更新游戏商游戏信息
* @author yangxy
* @version 创建时间：2023年11月26日 下午2:00:51 
*/
@Slf4j
@Component
public class GamerGameInfoScheduled {
	@Autowired
	private HttpUtil httpUtil;
	@Autowired
	private GamerService gamerService;
	@Autowired
	private GameService gameService;
	
	public void updateGameInfo() {
		List<GamerEntity> list = gamerService.list(new QueryWrapper<GamerEntity>().lambda().eq(GamerEntity::getStatus, 1));
		for(GamerEntity gamerEntity : list) {
			if(gamerEntity.getCode().equals("KM")) {
				updateGameIfonByKm(gamerEntity);
			}
		}
	}
	
	/**
	 * 更新KM游戏商游戏列表
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午2:04:44 
	* @param gamerEntity
	 */
	public void updateGameIfonByKm(GamerEntity gamerEntity) {
		Map<String,String> headerMap = Maps.newConcurrentMap();
		headerMap.put("X-QM-ClientId", gamerEntity.getClientId());
		headerMap.put("X-QM-ClientSecret", gamerEntity.getClientSecret());
		ResponseEntity<String> doGet = httpUtil.doGet("https://staging-api.queenmakergames.co/api/games?lang=zh-CN&platformtype=1", headerMap);
		String body = doGet.getBody();
		JSONObject parseObject2 = JSON.parseObject(body);
		List<KmGameDto> kmGameList = JSONObject.parseArray(parseObject2.getString("games"), KmGameDto.class);
		List<GameEntity> gameList = gameService.list();
		List<GameEntity> inserList = Lists.newArrayList();
		List<GameEntity> updateList = Lists.newArrayList();
		for(KmGameDto kmGameDto : kmGameList) {
			boolean flag = true;//是否新增游戏
			for(GameEntity gameEntity : gameList) {
				if(kmGameDto.getExternalid().equals(gameEntity.getThreeId())) {
					flag = false;
					if(kmGameDto.getIsactive() && gameEntity.getStatus() == 2) {//游戏由游戏商维护中变为启用
						gameEntity.setStatus(1);
						updateList.add(gameEntity);
					}else if(!kmGameDto.getIsactive() && gameEntity.getStatus() == 1) {//游戏由启用变为启用游戏商维护中
						gameEntity.setStatus(2);
						updateList.add(gameEntity);
					}
					break;
				}
			}
			if(flag) {
				GameEntity gameEntity = new GameEntity();
				gameEntity.setCode(kmGameDto.getCode());
				gameEntity.setProviderCode(kmGameDto.getProvidercode());
				gameEntity.setName(kmGameDto.getName());
				gameEntity.setGamerId(gamerEntity.getId());
				gameEntity.setThreeId(kmGameDto.getExternalid());
				gameEntity.setStatus(kmGameDto.getIsactive() ? 1:2);
				if(kmGameDto.getType() == 0) {
					gameEntity.setType(1);
				}else if(kmGameDto.getType() == 1) {
					gameEntity.setType(0);
				}
				
				inserList.add(gameEntity);
			}
		}
		if(!inserList.isEmpty()) {
			gameService.saveBatch(inserList);
		}
		if(!updateList.isEmpty()) {
			gameService.updateBatchById(updateList);
		}
	}
}
