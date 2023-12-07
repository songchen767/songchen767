package com.aia.scheduled;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/** 
 * 示例定时器
* @author yangxy
* @version 创建时间：2023年10月26日 上午9:16:15 
*/
@Slf4j
@Component
public class DemoScheduled {
	public void demo() {
		log.info("执行示例定时器");
	}
}
