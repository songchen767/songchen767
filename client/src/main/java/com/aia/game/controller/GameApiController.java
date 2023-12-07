package com.aia.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GameEntity;
import com.aia.game.req.CreditReq;
import com.aia.game.req.DeditReq;
import com.aia.game.req.EnterGameReq;
import com.aia.game.req.GameApiPageResp;
import com.aia.game.service.GameApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午5:55:53 
*/
@RestController
@RequestMapping("/game")
@Api(tags = "游戏对外相关接口")
public class GameApiController {
	@Autowired
	private GameApiService gameApiService;
	
	@HasLogin
	@GetMapping("/page")
	@ApiOperation(value = "分页游戏信息")
	public ApiResp<PageResp<GameEntity>> page(GameApiPageResp gameApiPageResp){
		return gameApiService.page(gameApiPageResp);
	}
	
	@HasLogin
	@PostMapping("/enterGame")
	@ApiOperation(value = "进入游戏")
	public ApiResp<String> enterGame(@RequestBody @Valid EnterGameReq enterGameReq){
		return gameApiService.enterGame(enterGameReq);
	}
	
	@HasLogin
	@GetMapping("/gamerbalance/{gammerId}")
	@ApiOperation(value = "获取当前会员在游戏商余额")
	@ApiImplicitParam(name = "gammerId",value = "游戏商ID",required = true)
	public ApiResp<Double> gamerbalance(@PathVariable("gammerId") Long gammerId,HttpServletRequest request){
		return gameApiService.gamerbalance(gammerId,request);
	}
	
	@HasLogin
	@PostMapping("/credit")
	@ApiOperation(value = "向游戏商钱包增加余额")
	public ApiResp<String> credit(@RequestBody @Valid CreditReq creditReq){
		return gameApiService.credit(creditReq);
	}
	
	@HasLogin
	@PostMapping("/debit")
	@GetMapping(value = "向游戏商钱包扣除余额")
	public ApiResp<String> debit(@RequestBody @Valid DeditReq deditReq){
		return gameApiService.debit(deditReq);
	}
}
