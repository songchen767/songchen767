package com.aia.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.aia.dto.LoginDto;
import com.aia.exception.LoginException;
import com.aia.util.SecurityFrameworkUtils;

/** 
* @author yangxy
* @version 创建时间：2023年7月27日 下午5:15:42 
*/
@Aspect
@Order(-1)
@Component
public class HasLoginAop {
	@Around("@annotation(com.aia.annotations.HasLogin)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		if(ObjectUtils.isEmpty(loginUser)) {
			throw new LoginException("未登录");
		}
        return joinPoint.proceed();
    }
}
