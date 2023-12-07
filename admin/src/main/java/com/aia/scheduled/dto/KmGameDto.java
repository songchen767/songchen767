package com.aia.scheduled.dto;

import lombok.Data;

/** 
 * KM游戏商游戏对象
* @author yangxy
* @version 创建时间：2023年11月26日 下午2:11:35 
*/
@Data
public class KmGameDto {
	/**
	 * 游戏供应商游戏专属辨识代码
	 */
	private String externalid;
	
	/**
	 * 游戏代码。
	 */
	private String code;
	
	/**
	 * 游戏名称
	 */
	private String name;
	
	/**
	 * 游戏说明
	 */
	private String description;
	
	/**
	 * 游戏供应商代码
	 */
	private String providercode;
	
	/**
	 * true/false表示游戏的状态。若游戏或其供应商在维护中，此参数值为false；否则为true。
	 */
	private Boolean isactive;
	
	/**
	 * 游戏类型（0 老虎机，1桌上游戏）
	 */
	private Integer type;
	
	/**
	 * 无法使用。请从我们的支持团队获取游戏图标
	 */
	private String iconurl;
	
	/**
	 * 
	 */
	private String betlimits;
	
	/**
	 * true/false表示游戏是否提供试玩。若游戏提供试玩，此参数值为true；否则false
	 */
	private Boolean supportdemourl;
}
