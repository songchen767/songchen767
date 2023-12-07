package com.aia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import com.aia.util.DefaultProfileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.aia.**.mapper")
public class AgentApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(AgentApplication.class);
	      DefaultProfileUtil.addDefaultProfile(app);
	      Environment env = app.run(args).getEnvironment(); 
	      log.info("\n----------------------------------------------------------\n\t" +
	                  "系统服务{} 启动成功! " +
	                  "端口号{}\n\t" +
	                  "运行环境{}\n\t"+
	                  "----------------------------------------------------------",
	            env.getProperty("spring.application.name"),
	            env.getProperty("server.port"),
	            env.getActiveProfiles().length == 0 ? env.getDefaultProfiles():env.getActiveProfiles());
    }
}
