package com.aia.auth;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.aia.dto.LoginDto;
import com.aia.exception.LoginException;
import com.aia.util.SecurityFrameworkUtils;

/** 
 * 自定义权限校验
* @author yangxy
* @version 创建时间：2023年7月26日 下午3:01:20 
*/
public class SecurityAuth {
	
	public boolean hasPermission(String permission) {
		return hasAnyPermissions(permission);
	}
	
	public boolean hasAnyPermissions(String... permissions) {
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		if(ObjectUtils.isEmpty(loginUser)) {
			throw new LoginException("未登录");
		}
		if(loginUser.getIsAdmin() == 1) {//超级管理员不校验权限
			return true;
		}
		List<String> authCods = SecurityFrameworkUtils.getLoginUser().getAuthCods();
		for(String permission:permissions) {
			if(authCods.contains(permission)) {
				return true;
			}
		}
		return false;
    }
	
    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    public boolean hasAnyRoles(String... roles) {
    	LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
    	if(ObjectUtils.isEmpty(loginUser)) {
			throw new LoginException("未登录");
		}
		if(loginUser.getIsAdmin() == 0) {//超级管理员不校验权限
			return true;
		}
    	List<String> roleCodes = SecurityFrameworkUtils.getLoginUser().getRoleCodes();
		for(String role:roles) {
			if(roleCodes.contains(role)) {
				return true;
			}
		}
		return false;
    }
}
