package com.aia.system.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aia.util.TaskUtil;

import lombok.extern.slf4j.Slf4j;

/** 
* @author yangxy
* @version 创建时间：2023年10月26日 上午9:17:31 
*/
@Slf4j
@Component
public class ScheduledInit implements CommandLineRunner{
	@Autowired
	private TaskUtil taskUtil;
	
	public void run(String... args) throws Exception {
		taskUtil.init();
		log.info("定时器初始化完成");
	}
}
