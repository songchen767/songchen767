package com.aia.aop;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aia.annotations.LogOperate;
import com.aia.dto.LoginDto;
import com.aia.enums.AmqEnums;
import com.aia.system.entity.SysLogEntity;
import com.aia.util.AmqUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Maps;

import me.codeleep.jsondiff.common.model.Defects;
import me.codeleep.jsondiff.common.model.JsonCompareResult;
import me.codeleep.jsondiff.common.model.JsonComparedOption;
import me.codeleep.jsondiff.common.model.TravelPath;
import me.codeleep.jsondiff.core.DefaultJsonDifference;

/** 
 * 日志切面处理
* @author yangxy
* @version 创建时间：2023年7月25日 下午4:45:05 
*/
@Aspect
@Component
public class LogAop {
	@Autowired
	private AmqUtils amqUtils;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Pointcut("@annotation(com.aia.annotations.LogOperate)")
	public void aopPoint() {
	}
	
	@Around("aopPoint()")
	public Object aroundAop(ProceedingJoinPoint pj) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) pj.getSignature();
		Method method = methodSignature.getMethod();
		LogOperate logOperate = method.getAnnotation(LogOperate.class);
		// 请求的方法参数值
		Object[] args = pj.getArgs();
		// 请求的方法参数名称
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		Map<String,Object> map = Maps.newHashMap();
		if (args != null && paramNames != null) {
		   String params = "";
		   for (int i = 0; i < args.length; i++) {
		      params += "  " + paramNames[i] + ": " + args[i];
		      map.put( paramNames[i], args[i]);
		   }
		}
		SysLogEntity sysLog = new SysLogEntity();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		sysLog.setIp(getIP(request));
		sysLog.setOperate(logOperate.operate());
		LoginDto loginUser = SecurityFrameworkUtils.getLoginUser();
		if(!ObjectUtils.isEmpty(loginUser)) {
//			sysLog.setCreator(loginUser.getUsername());
		}
		Long id = null;
		if(!map.containsKey("id") && paramNames.length >0 ) {
			String jsonString = JSON.toJSONString(pj.getArgs()[0]);
			com.alibaba.fastjson.JSONObject parseObject = com.alibaba.fastjson.JSON.parseObject(jsonString);
			if(parseObject.containsKey("id")) {
				id = parseObject.getLong("id");
			}
		}else if(!map.isEmpty()){
			id = (Long) map.get("id");
		}
		
		if("add".equals(logOperate.type())) {
			Object proceed = pj.proceed();
			sysLog.setDataChange(JSON.toJSONString(pj.getArgs()[0]));
			amqUtils.sendMqMsg(AmqEnums.OPERATE_LOG_AMQ.exchangeName, AmqEnums.OPERATE_LOG_AMQ.routeKey, com.alibaba.fastjson.JSON.toJSONString(sysLog));
			return proceed;
		}else if("edit".equals(logOperate.type()) && !ObjectUtils.isEmpty(id)){
			Class clz = logOperate.service();
			IService<?> iservice = (IService<?>) applicationContext.getBean(clz);
			Object byId = iservice.getById(id);
			boolean diff = true;
			if(ObjectUtils.isEmpty(byId)) {
				diff = false;
			}
			Object proceed = pj.proceed();
			Object byId2 = iservice.getById(id);
			Map<String, String> jsonDiffMap = this.jsonDiff(byId, byId2);
			if(!jsonDiffMap.isEmpty() && diff) {
				sysLog.setDataChange(com.alibaba.fastjson.JSON.toJSONString(jsonDiffMap));
				amqUtils.sendMqMsg(AmqEnums.OPERATE_LOG_AMQ.exchangeName, AmqEnums.OPERATE_LOG_AMQ.routeKey, com.alibaba.fastjson.JSON.toJSONString(sysLog));
			}
			return proceed;
		}if("del".equals(logOperate.type()) && !ObjectUtils.isEmpty(id)) {
			Class clz = logOperate.service();
			IService<?> iservice = (IService<?>) applicationContext.getBean(clz);
			Object byId = iservice.getById(id);
			if(ObjectUtils.isEmpty(byId)) {
				return pj.proceed();
			}
			Object proceed = pj.proceed();
			sysLog.setDataChange(com.alibaba.fastjson.JSON.toJSONString(byId));
			amqUtils.sendMqMsg(AmqEnums.OPERATE_LOG_AMQ.exchangeName, AmqEnums.OPERATE_LOG_AMQ.routeKey, com.alibaba.fastjson.JSON.toJSONString(sysLog));
			return proceed;
		}else {
			return pj.proceed();
		}
	}
	
	private Map<String,String> jsonDiff(Object obj1,Object obj2) {
		Map<String,String> map = Maps.newHashMap();
		String jsonString = JSON.toJSONString(obj1);
		JSONObject parseObject = JSON.parseObject(jsonString);
		String jsonString2 = JSON.toJSONString(obj2);
		JSONObject parseObject2 = JSON.parseObject(jsonString2);
		JsonComparedOption jsonComparedOption = new JsonComparedOption().setIgnoreOrder(true);
		JsonCompareResult jsonCompareResult = new DefaultJsonDifference().option(jsonComparedOption).detectDiff(parseObject, parseObject2);
		List<Defects> defectsList = jsonCompareResult.getDefectsList();
		for(Defects defects: defectsList) {
			TravelPath travelPath = defects.getTravelPath();
			String expectTravelPath = travelPath.getExpectTravelPath();
			String replace = expectTravelPath.replace("root.", "");
			if("null".equals(replace)) {
				String replace2 = travelPath.getActualTravelPath().replace("root.", "");
				if("creator".equals(replace2) || "updater".equals(replace2)
						|| "createdAt".equals(replace2)|| "updatedAt".equals(replace2)) {
					continue;
				}
				Object actual = defects.getActual();
				map.put(replace2, String.format("%s->%s", null,actual));
			}else {
				String replace2 = travelPath.getActualTravelPath().replace("root.", "");
				if("creator".equals(replace2) || "updater".equals(replace2)
						|| "createdAt".equals(replace2)|| "updatedAt".equals(replace2)) {
					continue;
				}
				Object actual = defects.getActual();
				Object expect = defects.getExpect();
				map.put(replace2, String.format("%s->%s", expect,actual));
			}
		}
		return map;
	}
		
	
	/***
	 * 获取客户端ip地址
	 * 
	 * @param request
	 */
	private String getIP(final HttpServletRequest request) throws Exception {
		if (request == null) {
			throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
		}
		String ipStr = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getRemoteAddr();
		}

		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ipStr.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ipStr = str;
				break;
			}
		}
		// 目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
		return ipStr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ipStr;
	}

}
