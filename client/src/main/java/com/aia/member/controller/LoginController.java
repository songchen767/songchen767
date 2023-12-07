package com.aia.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.member.req.LoginReq;
import com.aia.member.service.LoginService;
import com.aia.resp.LoginResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午6:57:25 
*/
@RestController
@RequestMapping("/member")
@Api(tags = "会员登录登出")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/login")
	@ApiOperation(value = "会员登录")
	public ApiResp<LoginResp> login(@RequestBody @Valid LoginReq loginReq){
		return loginService.login(loginReq);
	}
	
	@HasLogin
	@GetMapping("/loginout")
	@ApiOperation(value = "会员登出")
	public ApiResp<String> loginout(HttpServletRequest request){
		return loginService.loginout(request);
	}
}
