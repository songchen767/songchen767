package com.aia.game.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GameEntity;
import com.aia.game.req.CreditReq;
import com.aia.game.req.DeditReq;
import com.aia.game.req.EnterGameReq;
import com.aia.game.req.GameApiPageResp;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午5:57:14 
*/
public interface GameApiService {
	/**
	 * 分页游戏信息
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午6:17:31 
	* @param gameApiPageResp
	* @return
	 */
	public ApiResp<PageResp<GameEntity>> page(GameApiPageResp gameApiPageResp);
	
	/**
	 * 分页游戏信息
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午6:17:37 
	* @param enterGameReq
	* @return
	 */
	public ApiResp<String> enterGame(EnterGameReq enterGameReq);
	
	/**
	 * 获取当前会员在游戏商余额余额
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午8:07:23 
	* @return
	 */
	public ApiResp<Double> gamerbalance(Long gammerId,HttpServletRequest request);
	
	/**
	 * 向游戏商钱包增加余额
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午9:51:30 
	* @param creditReq
	* @return
	 */
	public ApiResp<String> credit(CreditReq creditReq);
	
	/**
	 * 向游戏商钱包增加余额
	* @author yangxy
	* @version 创建时间：2023年11月28日 上午9:51:38 
	* @param creditReq
	* @return
	 */
	public ApiResp<String> debit(DeditReq deditReq);
}
