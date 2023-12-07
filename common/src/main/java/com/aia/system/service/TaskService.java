package com.aia.system.service;

import java.lang.reflect.InvocationTargetException;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.system.entity.TaskEntity;
import com.aia.system.resp.CreateTaskRsp;
import com.aia.system.resp.PageTaskRsp;
import com.aia.system.resp.UpdateTaskRsp;
import com.baomidou.mybatisplus.extension.service.IService;

/** 
* @author yangxy
* @version 创建时间：2023年10月25日 下午3:20:24 
*/
public interface TaskService extends IService<TaskEntity> {
	/**
	 * 添加定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:34:47 
	* @param createTaskDto
	 */
	public ApiResp<String> addTask(CreateTaskRsp createTaskDto);
	
	/**
	 * 更新定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:34:56 
	* @param updateTaskDto
	 */
	public ApiResp<String> updateTask(UpdateTaskRsp updateTaskDto);
	
	/**
	 * 停用指定定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:35:09 
	* @param id
	 */
	public ApiResp<String> stopTask(Long id);
	
	/**
	 * 启用指定定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:35:09 
	* @param id
	 */
	public ApiResp<String> runTask(Long id);
	
	/**
	 * 停用所有定时
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午2:35:17
	 */
	public ApiResp<String> stopAllTask();
	
	/**
	 * 分页查询定时器
	* @author yangxy
	* @version 创建时间：2023年8月15日 下午3:22:03 
	* @param pageTaskDto
	* @return
	 */
	public ApiResp<PageResp<TaskEntity>> page(PageTaskRsp pageTaskDto);
	
	/**
	 * 立即执行指定定时器
	* @author yangxy
	* @version 创建时间：2023年9月4日 下午2:35:59 
	* @param id
	* @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public ApiResp<PageResp<TaskEntity>> run(Long id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
