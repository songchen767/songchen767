package com.aia.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.demo.entity.Demo;
import com.aia.demo.mapper.DemoMapper;
import com.aia.demo.req.DemoAddReq;
import com.aia.demo.req.DemoEditReq;
import com.aia.demo.req.DemoPageReq;
import com.aia.demo.service.DemoService;
import com.aia.exception.ApiBussException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午4:49:57 
*/
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

	@Override
	@SuppressWarnings("unchecked")
	public ApiResp<PageResp<Demo>> page(DemoPageReq demoPageReq) {
		// TODO Auto-generated method stub
		LambdaQueryWrapper<Demo> lambdaQuery = new QueryWrapper<Demo>().lambda();
		
		if(!StringUtils.isEmpty(demoPageReq.getUserName())) {
			lambdaQuery.like(Demo::getUserName, demoPageReq.getUserName());
		}
		if(!ObjectUtils.isEmpty(demoPageReq.getType())) {
			lambdaQuery.eq(Demo::getType, demoPageReq.getType());
		}
		
		PageHelper.startPage(demoPageReq.getPageNo(), demoPageReq.getPageSize());
		Page<Demo> page = (Page<Demo>) list(lambdaQuery);
		return ApiResp.page(page);
	}

	@Override
	public ApiResp<String> add(DemoAddReq demoAddReq) {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		BeanUtils.copyProperties(demoAddReq, demo);
		save(demo);
		return ApiResp.sucess();
	}

	@Override
	public ApiResp<String> eidt(DemoEditReq demoEditReq) {
		// TODO Auto-generated method stub
		Demo demo = getById(demoEditReq.getId());
		if(ObjectUtils.isEmpty(demo)) {
			throw new ApiBussException("数据不存在");
		}
		BeanUtils.copyProperties(demoEditReq, demo);
		updateById(demo);
		return ApiResp.sucess();
	}

	@Override
	public ApiResp<String> del(Long id) {
		// TODO Auto-generated method stub
		removeById(id);
		return ApiResp.sucess();
	}

	@Override
	public void sendAmqMsg() {
		// TODO Auto-generated method stub
		
	}

}
