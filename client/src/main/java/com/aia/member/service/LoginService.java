package com.aia.member.service;

import javax.servlet.http.HttpServletRequest;

import com.aia.base.resp.ApiResp;
import com.aia.member.req.LoginReq;
import com.aia.resp.LoginResp;

import io.swagger.annotations.ApiOperation;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午6:58:47 
*/
public interface LoginService {
	/**
	 * 会员登录
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午7:06:18 
	* @param loginReq
	* @return
	 */
	public ApiResp<LoginResp> login(LoginReq loginReq);
	
	/**
	 * 会员登出
	* @author yangxy
	* @version 创建时间：2023年11月26日 下午7:19:38 
	* @return
	 */
	public ApiResp<String> loginout(HttpServletRequest request);
}
