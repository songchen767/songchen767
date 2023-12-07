package com.aia.amq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aia.system.entity.SysLogEntity;
import com.aia.system.service.SysLogService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/** 
 * 操作日志消息队列监听
* @author yangxy
* @version 创建时间：2023年10月25日 上午9:48:33 
*/
@Slf4j
@Component
public class OperateLogConsumer {
	@Autowired
	private SysLogService sysLogService;
	
	@RabbitListener(queues = "#{operateLogQueue.name}",concurrency = "10")
	public void demoQueueListener(String message) {
		log.info("消费消息：{}",message);
		SysLogEntity sysLog = JSONObject.toJavaObject(JSON.parseObject(message), SysLogEntity.class);
		sysLogService.save(sysLog);
	}
}
