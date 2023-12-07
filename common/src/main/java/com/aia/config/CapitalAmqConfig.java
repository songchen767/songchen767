package com.aia.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aia.enums.AmqEnums;

/** 
* @author yangxy
* @version 创建时间：2023年10月26日 下午5:20:33 
*/
@Configuration
public class CapitalAmqConfig {
	/**
	 * 初始化提现审核队列并绑定死信队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue cashAuditQueue() {
		Map<String, Object> arguments = new HashMap<>(2);
		// 死信交换机
		arguments.put("x-dead-letter-exchange", AmqEnums.CASH_DEAL_AUDIT_AMQ.exchangeName);
		// 死信队列
		arguments.put("x-dead-letter-routing-key", AmqEnums.CASH_DEAL_AUDIT_AMQ.routeKey);
		return new Queue(AmqEnums.CASH_AUDIT_AMQ.queueName, true, false, false, arguments);
	}

	/**
	 * 初始化现审核队列交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange cashAuditExchange() {
		return new DirectExchange(AmqEnums.CASH_AUDIT_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定提现审核队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingCashAudit() {
		return BindingBuilder.bind(cashAuditQueue()).to(cashAuditExchange()).with(AmqEnums.CASH_AUDIT_AMQ.routeKey);
	}
	
	/**
	 * 声明提现审核死信队列交换机
	 * 
	 * @return DirectExchange
	 */
	@Bean
	public DirectExchange cashAuditDealExchange() {
		return new DirectExchange(AmqEnums.CASH_DEAL_AUDIT_AMQ.exchangeName);
	}

	/**
	 * 声明提现审核死信队列
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue cashAuditDealQueue() {
		return new Queue(AmqEnums.CASH_DEAL_AUDIT_AMQ.queueName);
	}

	/**
	 * 绑定充值审核死信队列到交换机
	 * 
	 * @return Binding
	 */
	@Bean
	public Binding bindingCashAuditDeal() {
		return BindingBuilder.bind(cashAuditDealQueue()).to(cashAuditDealExchange()).with(AmqEnums.CASH_DEAL_AUDIT_AMQ.routeKey);
	}
	
	/**
	 * 初始化充值审核队列并绑定死信队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue incomeAuditQueue() {
		Map<String, Object> arguments = new HashMap<>(2);
		// 死信交换机
		arguments.put("x-dead-letter-exchange", AmqEnums.INCOME_DEAL_AUDIT_AMQ.exchangeName);
		// 死信队列
		arguments.put("x-dead-letter-routing-key", AmqEnums.INCOME_DEAL_AUDIT_AMQ.routeKey);
		return new Queue(AmqEnums.INCOME_AUDIT_AMQ.queueName, true, false, false, arguments);
	}

	/**
	 * 初始化充值审核队列交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange incomeAuditExchange() {
		return new DirectExchange(AmqEnums.INCOME_AUDIT_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定提现审核队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingIncomeAudit() {
		return BindingBuilder.bind(incomeAuditQueue()).to(incomeAuditExchange()).with(AmqEnums.INCOME_AUDIT_AMQ.routeKey);
	}
	
	/**
	 * 声明充值审核死信队列交换机
	 * 
	 * @return DirectExchange
	 */
	@Bean
	public DirectExchange incomeAuditDealExchange() {
		return new DirectExchange(AmqEnums.INCOME_DEAL_AUDIT_AMQ.exchangeName);
	}

	/**
	 * 声明充值审核死信队列
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue incomeAuditDealQueue() {
		return new Queue(AmqEnums.INCOME_DEAL_AUDIT_AMQ.queueName);
	}

	/**
	 * 绑定提现审核死信队列到交换机
	 * 
	 * @return Binding
	 */
	@Bean
	public Binding bindingIncomeAuditDeal() {
		return BindingBuilder.bind(incomeAuditDealQueue()).to(incomeAuditDealExchange()).with(AmqEnums.INCOME_DEAL_AUDIT_AMQ.routeKey);
	}
	
	/**
	 * 初始化充值过期状态修改延迟队列并绑定死信队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue incomeStatusQueue() {
		Map<String, Object> arguments = new HashMap<>(2);
		// 死信交换机
		arguments.put("x-dead-letter-exchange", AmqEnums.INCOME_DEAL_STATUS_AMQ.exchangeName);
		// 死信队列
		arguments.put("x-dead-letter-routing-key", AmqEnums.INCOME_DEAL_STATUS_AMQ.routeKey);
		return new Queue(AmqEnums.INCOME_STATUS_AMQ.queueName, true, false, false, arguments);
	}

	/**
	 * 初始化充值过期状态修改延迟队列交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	CustomExchange incomeStatusExchange() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-delayed-type", "direct");
		return new CustomExchange(AmqEnums.INCOME_STATUS_AMQ.exchangeName, "x-delayed-message", true, false, args);
//		return new DirectExchange(AmqEnums.INCOME_STATUS_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定充值过期状态修改延迟队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingIncomeStatus() {
//		return BindingBuilder.bind(incomeStatusQueue()).to(incomeStatusExchange()).with(AmqEnums.INCOME_STATUS_AMQ.routeKey);
		return BindingBuilder.bind(incomeStatusQueue()).to(incomeStatusExchange()).with(AmqEnums.INCOME_STATUS_AMQ.routeKey).noargs();
	}
	
	/**
	 * 声明充值审核死信队列交换机
	 * 
	 * @return DirectExchange
	 */
	@Bean
	public DirectExchange incomeStatusDealExchange() {
		return new DirectExchange(AmqEnums.INCOME_DEAL_STATUS_AMQ.exchangeName);
	}

	/**
	 * 声明充值审核死信队列
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue incomeStatusDealQueue() {
		return new Queue(AmqEnums.INCOME_DEAL_STATUS_AMQ.queueName);
	}

	/**
	 * 绑定提现审核死信队列到交换机
	 * 
	 * @return Binding
	 */
	@Bean
	public Binding bindingIncomeStatusDeal() {
		return BindingBuilder.bind(incomeStatusDealQueue()).to(incomeStatusDealExchange()).with(AmqEnums.INCOME_DEAL_STATUS_AMQ.routeKey);
	}
}
