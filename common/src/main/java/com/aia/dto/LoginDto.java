package com.aia.dto;

import java.util.List;

import lombok.Data;

/** 
 * 用户登录实体对象
* @author yangxy
* @version 创建时间：2023年10月24日 上午11:48:52 
*/
@Data
public class LoginDto {
	private Long userId;
	
	/**
	 * 是否为超级管理员（1 是；0否）
	 */
	private Integer isAdmin;
	
	/**
	 * 用户拥有角色编码列表
	 */
	private List<String> roleCodes;
	
	/**
	 * 用户拥有权限编码列表
	 */
	private List<String> authCods;
	
	/**
	 * 用户名
	 */
	private String userName;
}
