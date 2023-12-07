package com.aia.enums;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午7:59:57 
*/
public enum AmqEnums {
	/**
	 * 钱包余额变动队列
	 */
	WALLET_CHANGE_AMQ( "wallet_change_queue","wallet_change_routing","wallet_change_exchange",1),
	/**
	 * 钱包余额变动记录队列
	 */
	WALLET_CHANGE_RECORD_AMQ( "wallet_change_record_queue","wallet_change_record_routing","wallet_change_record_exchange",1),
	/**
	 * 操作日志队列
	 */
	OPERATE_LOG_AMQ( "operate_log_queue","operate_log_routing","operate_log_exchange",1),
	/**
	 * 提现审核队列
	 */
	CASH_AUDIT_AMQ("cash_audit_queue","cash_audit_routing","cash_audit_exchange", 1),
	/**
	 * 提现审核死信队列
	 */
	CASH_DEAL_AUDIT_AMQ( "cash_audit_deal_queue","cash_audit_deal_routing","cash_audit_deal_exchange",1),
	/**
	 * 充值审核队列
	 */
	INCOME_AUDIT_AMQ( "income_audit_queue","income_audit_routing","income_audit_exchange",1),
	/**
	 * 充值审核死信队列
	 */
	INCOME_DEAL_AUDIT_AMQ( "income_audit_deal_queue","income_audit_deal_routing","income_audit_deal_exchange",1),
	/**
	 * 充值过期状态修改延迟队列
	 */
	INCOME_STATUS_AMQ( "income_status_queue","income_status_routing","income_status_exchange",1),
	/**
	 * 充值过期状态修死信队列
	 */
	INCOME_DEAL_STATUS_AMQ("income_status_deal_queue","income_status_deal_routing","income_status_deal_exchange", 1),
	/**
	 * 示例普通队列
	 */
	DEMO_PT("demo_queue","demoRoute","demo_exchange",1),
	/**
	 * 示例死信队列
	 */
	DEMO_DEAL("demo_deal_queue","demoDealRoute","demo_deal_exchange",4);
	
	/**
	 * 队列名称
	 */
	public String queueName;
	
	/**
	 * 路由key
	 */
	public String routeKey;
	
	/**
	 * 交换机名称
	 */
	public String exchangeName;
	
	/**
	 * 队列类型（1 普通队列，2广播队列，3延迟队列,4死信队列）
	 */
	private int type;

	private AmqEnums(String queueName, String routeKey, String exchangeName, int type) {
		this.queueName = queueName;
		this.routeKey = routeKey;
		this.exchangeName = exchangeName;
		this.type = type;
	}

}
