package com.aia.system.controller;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.system.entity.TaskEntity;
import com.aia.system.resp.CreateTaskRsp;
import com.aia.system.resp.PageTaskRsp;
import com.aia.system.resp.UpdateTaskRsp;
import com.aia.system.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author yangxy
 * @version 创建时间：2023年8月15日 上午11:37:10
 */
@RestController
@RequestMapping("/sys/task")
@Api(tags = "定时器管理")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/addTask")
	@ApiOperation(value = "添加定时器")
	@LogOperate(operate = "添加定时器",service = TaskService.class,type = "add")
	public ApiResp<String> addTask(@RequestBody @Valid CreateTaskRsp createTaskDto) {
		return taskService.addTask(createTaskDto);
	}

	@PostMapping("/updateTask")
	@ApiOperation(value = "更新定时器")
	@LogOperate(operate = "更新定时器",service = TaskService.class,type = "edit")
	public ApiResp<String> updateTask(@RequestBody @Valid UpdateTaskRsp updateTaskDto) {
		return taskService.updateTask(updateTaskDto);
	}

	@GetMapping("/stopTask/{id}")
	@ApiOperation(value = "停用指定定时器")
	@ApiImplicitParam(name = "id", value = "定时器ID", required = true)
	public ApiResp<String> stopTask(@PathVariable("id") Long id) {
		return taskService.stopTask(id);
	}

	@GetMapping("/runTask/{id}")
	@ApiOperation(value = "启用指定定时器")
	@ApiImplicitParam(name = "id", value = "定时器ID", required = true)
	public ApiResp<String> runTask(@PathVariable("id") Long id) {
		return taskService.runTask(id);
	}

	@GetMapping("/stopAllTask")
	@ApiOperation(value = "停用所有定时")
	@LogOperate(operate = "停用所有定时",service = TaskService.class)
	public ApiResp<String> stopAllTask() {
		return taskService.stopAllTask();
	}

	@PostMapping("/page")
	@ApiOperation(value = "分页查询定时器")
	@LogOperate(operate = "分页查询定时器",service = TaskService.class)
	public ApiResp<PageResp<TaskEntity>> page(@RequestBody PageTaskRsp pageTaskDto) {
		return taskService.page(pageTaskDto);
	}

	@GetMapping("/run/{id}")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "定时器ID", required = true),
	})
	@ApiOperation(value = "立即执行指定定时器")
	public ApiResp<PageResp<TaskEntity>> run(@PathVariable("id") Long id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return taskService.run(id);
	}
}
