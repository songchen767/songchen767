package com.aia.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.aia.exception.BusinessException;
import com.aia.system.entity.TaskEntity;
import com.aia.system.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class TaskUtil {
	@Value("${system.code}")
	private String systemCode;
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private TaskService taskService;

	public static Map<String, ScheduledFuture<?>> futuresMap = new HashMap<String, ScheduledFuture<?>>();


	@Bean
	public ThreadPoolTaskScheduler trPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	/**
	 * 添加定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:25:45 
	* @param task
	 */
	public void addTask(TaskEntity task) {

		if(TaskUtil.futuresMap.containsKey(task.getTaskName())) {
			throw new BusinessException(String.format("定时器:%s已启用，请勿重复启用", task.getTaskName()));
		}
		ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(getRunnable(task), getTrigger(task));
		TaskUtil.futuresMap.put(task.getTaskName(), schedule);
	}
	
	/**
	 * 移除定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:25:26 
	* @param taskName
	 */
	public void removeTask(String taskName) {
		if(!TaskUtil.futuresMap.containsKey(taskName)) {
			return;
		}
		ScheduledFuture<?> task = TaskUtil.futuresMap.remove(taskName);
		task.cancel(true);
	}
	
	/**
	 * 更新定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:27:33 
	* @param task
	 */
	public void updateTask(TaskEntity task) {
		this.removeTask(task.getTaskName());
		ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(getRunnable(task), getTrigger(task));
		TaskUtil.futuresMap.put(task.getTaskName(), schedule);
	}

	/**
	 * 初始化加载定时器
	 */
	public void init() {
		List<TaskEntity> list = taskService.list(new QueryWrapper<TaskEntity>().lambda().eq(TaskEntity::getIsUse, 0).eq(TaskEntity::getSystemCode, systemCode));

		list.forEach(task -> {
			ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(getRunnable(task), getTrigger(task));
			TaskUtil.futuresMap.put(task.getTaskName(), schedule);
		});
	}

	/**
	 * 停止所有定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:27:53
	 */
	public void stopAllTask() {
		for(String key : TaskUtil.futuresMap.keySet()) {
			TaskUtil.futuresMap.get(key).cancel(true);
		}

	}

	/**
	 * 指定定时器执行方法
	 * 
	 * @param task
	 * @return
	 */
	private Runnable getRunnable(TaskEntity task) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					Object obj = applicationContext.getBean(task.getBeanName());
					Method method = obj.getClass().getMethod(task.getExcuteMethod());
					method.invoke(obj);
				} catch (InvocationTargetException e) {
					
					log.error("定时任务启动错误，反射异常:" + task.getBeanName() + ";" + task.getExcuteMethod() + ";"
							+ e.getMessage());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		};
	}

	/**
	 * 指定定时器执行频率
	 * 
	 * @param task
	 * @return
	 */
	private Trigger getTrigger(TaskEntity task) {
		return new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 将Cron 0/1 * * * * ? 输入取得下一次执行的时间
				CronTrigger trigger = new CronTrigger(task.getCron());
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};

	}

}
