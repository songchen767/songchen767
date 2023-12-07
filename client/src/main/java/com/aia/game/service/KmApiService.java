package com.aia.game.service;

import java.util.Date;

import com.aia.base.resp.ApiResp;
import com.aia.game.entity.GamerEntity;
import com.aia.game.req.EnterGameReq;

/** 
 * KM游戏商接口服务
* @author yangxy
* @version 创建时间：2023年11月28日 上午11:32:06 
*/
public interface KmApiService {

	/**
	 * 进入指定游戏
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午11:41:18 
	* @param enterGameReq 
	* @param gamerEntity 游戏商信息
	* @return
	 */
	public ApiResp<String> enterGame(EnterGameReq enterGameReq,GamerEntity gamerEntity);
	
	/**
	 * 获取当前会员在游戏商余额余额
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午11:42:07 
	* @param gamerEntity 游戏商信息
	* @return
	 */
	public ApiResp<Double> gamerbalance(GamerEntity gamerEntity);
	
	/**
	 * 向游戏商钱包增加余额
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午11:42:21 
	* @param gamerEntity 游戏商信息
	* @param amt 金额
	* @return
	 */
	public ApiResp<String> credit(GamerEntity gamerEntity,Double amt);
	
	/**
	 * 向游戏商钱包增加余额
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午11:42:41 
	* @param gamerEntity 游戏商信息
	* @param amt 金额
	* @return
	 */
	public ApiResp<String> debit(GamerEntity gamerEntity,Double amt);
	
	/**
	 * 获取游戏商投注历史
	* @author yangxy
	* @version 创建时间：2023年11月28日 下午12:17:14 
	* @param startTime 开始时间
	* @param endTime 结束时间
	 */
	public void getBetHistory(GamerEntity gamerEntity,String startTime,String endTime);
}
