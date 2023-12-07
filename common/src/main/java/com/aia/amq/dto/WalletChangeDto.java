package com.aia.amq.dto;

import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年11月28日 上午10:39:40 
*/
@Data
public class WalletChangeDto {
	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 用户类型（0 会员，1代理）
	 */
	private Integer userType;

	/**
	 * 变动类型（1提现，2充值，3转出到游戏商，4从游戏商转入）
	 */
	private Integer type;

	/**
	 * 变动金额
	 */
	private Double amt;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 变动前余额
	 */
	private Double beforeBalance;
	
	/**
	 * 变动后余额
	 */
	private Double afterBalance;
	
	/**
	 * 钱包ID
	 */
	private Long walletId;
}
