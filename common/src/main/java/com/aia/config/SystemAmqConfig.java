package com.aia.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aia.enums.AmqEnums;

/**
 * 系统相关消息队列配置
 * @author yangxy
 * @version 创建时间：2023年10月24日 下午8:05:55
 */
@Configuration
public class SystemAmqConfig {
	/**
	 * 初始化普通示例队列并绑定死信队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue demoQueue() {
		Map<String, Object> arguments = new HashMap<>(2);
		// 死信交换机
		arguments.put("x-dead-letter-exchange", AmqEnums.DEMO_DEAL.exchangeName);
		// 死信队列
		arguments.put("x-dead-letter-routing-key", AmqEnums.DEMO_DEAL.routeKey);
		return new Queue(AmqEnums.DEMO_PT.queueName, true, false, false, arguments);
	}

	/**
	 * 初始化普通示例交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange demoExchange() {
		return new DirectExchange(AmqEnums.DEMO_PT.exchangeName, true, false);
	}

	/**
	 * 绑定初始化普通示例队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingDemoHandler() {
		return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(AmqEnums.DEMO_PT.routeKey);
	}

	/**
	 * 声明死信交换机
	 * 
	 * @return DirectExchange
	 */
	@Bean
	public DirectExchange demoDealExchange() {
		return new DirectExchange(AmqEnums.DEMO_DEAL.exchangeName);
	}

	/**
	 * 声明死信队列
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue demoDealQueue() {
		return new Queue(AmqEnums.DEMO_DEAL.queueName);
	}

	/**
	 * 绑定死信队列到死信交换机
	 * 
	 * @return Binding
	 */
	@Bean
	public Binding bindingDemoDeal() {
		return BindingBuilder.bind(demoDealQueue()).to(demoDealExchange()).with(AmqEnums.DEMO_DEAL.routeKey);
	}
	
	/**
	 * 初始化操作日志队列
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:34
	 * @return
	 */
	@Bean
	public Queue operateLogQueue() {
		return new Queue(AmqEnums.OPERATE_LOG_AMQ.queueName, true);
	}

	/**
	 * 初始化操作日志交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:41:47
	 * @return
	 */
	@Bean
	DirectExchange operateLogExchange() {
		return new DirectExchange(AmqEnums.OPERATE_LOG_AMQ.exchangeName, true, false);
	}

	/**
	 * 绑定操作日志队列和交换机
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月19日 上午10:42:12
	 * @return
	 */
	@Bean
	Binding bindingOperateLog() {
		return BindingBuilder.bind(operateLogQueue()).to(operateLogExchange()).with(AmqEnums.OPERATE_LOG_AMQ.routeKey);
	}
}
