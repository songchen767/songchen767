package com.aia.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.HasLogin;
import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.demo.entity.Demo;
import com.aia.demo.req.DemoAddReq;
import com.aia.demo.req.DemoEditReq;
import com.aia.demo.req.DemoPageReq;
import com.aia.demo.service.DemoService;
import com.aia.enums.AmqEnums;
import com.aia.util.AmqUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午4:25:47 
*/
@RestController
@RequestMapping("/demo")
@Api(tags = "示例代码")
public class DemoController {
	@Autowired
	private DemoService demoService;
	@Autowired
	private AmqUtils amqUtils;
	
	@HasLogin
	@GetMapping("/checkLogin")
	@ApiOperation(value = "校验用户登录示例")
	public ApiResp<String> checkLogin(){
		return ApiResp.sucess();
	}
	
	@GetMapping("/checkAuth")
	@ApiOperation(value = "校验用户是否拥有某个权限示例")
	@PreAuthorize("@ss.hasAnyPermissions('system:curr:create')")
	public ApiResp<String> checkAuth() {
		return ApiResp.sucess();
	}
	
	@GetMapping("/checkRole")
	@ApiOperation(value = "校验用户是否拥有某个角色示例")
	@PreAuthorize("@ss.hasRole('system:curr:create')")
	public ApiResp<String> checkRole() {
		return ApiResp.sucess();
	}
	
	@GetMapping("/page")
	@ApiOperation(value = "分页查询示例")
	public ApiResp<PageResp<Demo>> page(DemoPageReq demoPageReq){
		return demoService.page(demoPageReq);
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "新增并记录数据变化操作日志示例")
	@LogOperate(operate = "添加",service = DemoService.class,type = DataOperateContants.ADD)
	public ApiResp<String> add(@RequestBody @Valid DemoAddReq demoAddReq) {
		return demoService.add(demoAddReq);
	}
	
	@PostMapping("/eidt")
	@ApiOperation(value = "修改并记录数据变化操作日志示例")
	@LogOperate(operate = "修改",service = DemoService.class,type = DataOperateContants.EDIT)
	public ApiResp<String> eidt(@RequestBody @Valid DemoEditReq demoEditReq) {
		return demoService.eidt(demoEditReq);
	}
	
	@GetMapping("/del/{id}")
	@ApiOperation(value = "删除并记录数据删除操作日志示例")
	@LogOperate(operate = "删除",service = DemoService.class,type = DataOperateContants.DEL)
	@ApiImplicitParam(name = "id",value = "ID",required = true)
	public ApiResp<String> del(@PathVariable("id") Long id) {
		return demoService.del(id);
	}
	
	@PostMapping("/sendAmqMsg")
	@ApiOperation(value = "向消息队列推送消息示例")
	public void sendAmqMsg(@RequestBody Demo demo) {
		amqUtils.sendMqMsg(AmqEnums.DEMO_PT.exchangeName, AmqEnums.DEMO_PT.routeKey, demo);
	}
}
