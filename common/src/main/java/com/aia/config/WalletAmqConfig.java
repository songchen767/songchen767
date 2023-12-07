package com.aia.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aia.enums.AmqEnums;

/** 
 * 钱包处理相关消息队列配置
* @author yangxy
* @version 创建时间：2023年10月25日 上午9:40:56 
*/
@Configuration
public class WalletAmqConfig {
	/******************钱包余额变动队列配置***********************/
	/**
	 * 初始化钱包余额变动队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue walletChangeQueue() {
		return new Queue(AmqEnums.WALLET_CHANGE_AMQ.queueName, true);
	}

	/**
	 * 初始化钱包余额变动交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange walletChangeExchange() {
		return new DirectExchange(AmqEnums.WALLET_CHANGE_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定钱包余额变动队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingWalletChange() {
		return BindingBuilder.bind(walletChangeQueue()).to(walletChangeExchange()).with(AmqEnums.WALLET_CHANGE_AMQ.routeKey);
	}
	
	/******************钱包余额变动记录队列配置***********************/
	/**
	 * 初始化钱包余额变动记录队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue walletChangeRecordQueue() {
		return new Queue(AmqEnums.WALLET_CHANGE_RECORD_AMQ.queueName, true);
	}

	/**
	 * 初始化钱包余额变动记录交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange walletChangeRecordExchange() {
		return new DirectExchange(AmqEnums.WALLET_CHANGE_RECORD_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定钱包余额变动记录和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingWalletRecordChange() {
		return BindingBuilder.bind(walletChangeRecordQueue()).to(walletChangeRecordExchange()).with(AmqEnums.WALLET_CHANGE_RECORD_AMQ.routeKey);
	}
}
