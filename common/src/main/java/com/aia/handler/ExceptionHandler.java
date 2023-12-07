package com.aia.handler;

import java.nio.file.AccessDeniedException;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aia.base.resp.ApiResp;
import com.aia.exception.ApiBussException;
import com.aia.exception.AuthorityException;
import com.aia.exception.BusinessException;
import com.aia.exception.DecryptException;
import com.aia.exception.JWTExcepiton;
import com.aia.exception.LoginException;
import com.aia.exception.ParamException;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 * @author 27669
 *
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler {
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentNotValidException.class })
	public ApiResp<String> validationExceptionHandler(MethodArgumentNotValidException e) {
		String msg = e.getBindingResult().getFieldError().getDefaultMessage();
		return ApiResp.paramError(msg);
	}

	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
	public ApiResp<String> exceptionHandler(Exception e) {
		log.error("", e);
		return ApiResp.sysError();
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ LoginException.class })
	public ApiResp<String> loginHandler(LoginException e) {
		return ApiResp.authError(e.getMsg());
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ AuthorityException.class })
	public ApiResp<String> authHandler(AuthorityException e) {
		return ApiResp.authError(e.getMsg());
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ JWTExcepiton.class })
	public ApiResp<String> authHandler(JWTExcepiton e) {
		return ApiResp.jwtError(e.getMsg());
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ AccessDeniedException.class })
	public ApiResp<String> accessDeniedHandler(AccessDeniedException e) {
		return ApiResp.authError("没有权限");
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ BusinessException.class })
	public ApiResp<String> accessDeniedHandler(BusinessException e) {
		return ApiResp.bussError(e.getMsg());
	}
	
	@ResponseBody
	@SuppressWarnings("unchecked")
	@org.springframework.web.bind.annotation.ExceptionHandler({ ApiBussException.class })
	public ApiResp<String> apiBussExceptionHandler(ApiBussException e) {
		return ApiResp.bussError(e.getMsg());
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler({ DecryptException.class })
	public Map<String,String> decryptExceptionHandler(DecryptException e) {
		Map<String,String> map = Maps.newHashMap();
		map.put("error_code", e.getErrCode());
		return map;
	}
	
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler({ ParamException.class })
	public Map<String,Object> paramExceptionHandler(ParamException e) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("error_code", e.getErrCode());
		return map;
	}
}
