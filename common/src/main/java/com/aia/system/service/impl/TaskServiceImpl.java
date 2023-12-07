package com.aia.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.BusinessException;
import com.aia.system.entity.TaskEntity;
import com.aia.system.mapper.TaskMapper;
import com.aia.system.resp.CreateTaskRsp;
import com.aia.system.resp.PageTaskRsp;
import com.aia.system.resp.UpdateTaskRsp;
import com.aia.system.service.TaskService;
import com.aia.util.TaskUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author yangxy
 * @version 创建时间：2023年10月25日 下午3:21:01
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {
	@Value("${system.code}")
	private String systemCode;
	@Autowired
	private TaskUtil taskUtil;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<String> addTask(CreateTaskRsp createTaskDto) {
		// TODO Auto-generated method stub
		TaskEntity taskEntity = new TaskEntity();
		BeanUtils.copyProperties(createTaskDto, taskEntity);
		taskEntity.setSystemCode(systemCode);
		save(taskEntity);

		if (taskEntity.getIsUse() == 0) {
			taskUtil.addTask(taskEntity);
		}
		return ApiResp.sucess();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<String> updateTask(UpdateTaskRsp updateTaskDto) {
		// TODO Auto-generated method stub
		TaskEntity taskEntity = getById(updateTaskDto.getId());
		if (ObjectUtils.isEmpty(taskEntity)) {
			throw new BusinessException(String.format("定时器%s不存在", updateTaskDto.getId()));
		}

		TaskEntity one = getOne(new QueryWrapper<TaskEntity>().lambda()
				.eq(TaskEntity::getTaskName, updateTaskDto.getTaskName()).ne(TaskEntity::getId, updateTaskDto.getId()));
		if (!ObjectUtils.isEmpty(one)) {
			throw new BusinessException(String.format("定时器名称%s已存在", updateTaskDto.getTaskName()));
		}

		BeanUtils.copyProperties(updateTaskDto, taskEntity);
		taskEntity.setSystemCode(systemCode);
		updateById(taskEntity);

		if (taskEntity.getIsUse() == 0) {
			taskUtil.updateTask(taskEntity);
		}
		return ApiResp.sucess();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<String> stopTask(Long id) {
		// TODO Auto-generated method stub
		TaskEntity taskEntity = getById(id);
		if (ObjectUtils.isEmpty(taskEntity)) {
			throw new BusinessException(String.format("定时器%s不存在", id));
		}

		taskEntity.setIsUse(1);
		updateById(taskEntity);
		taskUtil.removeTask(taskEntity.getTaskName());
		return ApiResp.sucess();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<String> stopAllTask() {
		// TODO Auto-generated method stub
		taskUtil.stopAllTask();
		return ApiResp.sucess();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<String> runTask(Long id) {
		// TODO Auto-generated method stub
		TaskEntity taskEntity = getById(id);
		if (ObjectUtils.isEmpty(taskEntity)) {
			throw new BusinessException(String.format("定时器%s不存在", id));
		}

		taskEntity.setIsUse(0);
		updateById(taskEntity);
		taskUtil.addTask(taskEntity);
		return ApiResp.sucess();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<PageResp<TaskEntity>> page(PageTaskRsp pageTaskDto) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<TaskEntity> lambdaQuery = new QueryWrapper<TaskEntity>().lambda().eq(TaskEntity::getSystemCode, systemCode);
		
		if(!StringUtils.isEmpty(pageTaskDto.getTaskName())) {
			lambdaQuery.like(TaskEntity::getTaskName, pageTaskDto.getTaskName());
		}
		if(!ObjectUtils.isEmpty(pageTaskDto.getIsUse())) {
			lambdaQuery.eq(TaskEntity::getIsUse, pageTaskDto.getIsUse());
		}
		PageHelper.startPage(pageTaskDto.getPageNo(), pageTaskDto.getPageSize());
		Page<TaskEntity> page = (Page<TaskEntity>) list(lambdaQuery);
		return ApiResp.page(page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<PageResp<TaskEntity>> run(Long id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		TaskEntity taskEntity = getById(id);
		if (ObjectUtils.isEmpty(taskEntity)) {
			return ApiResp.bussError("定时器不存在");
		}

		Object obj = applicationContext.getBean(taskEntity.getBeanName());
		Method method = obj.getClass().getMethod(taskEntity.getExcuteMethod());
		method.invoke(obj);
		return ApiResp.sucess();
	}
}
