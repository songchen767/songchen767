package com.aia.plug.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.exception.ApiBussException;
import com.aia.plug.req.MerchantPlugAddReq;
import com.aia.plug.req.MerchantPlugEditReq;
import com.aia.plug.req.MerchantPlugPageReq;
import com.aia.plug.entity.MerchantPlugEntity;
import com.aia.plug.mapper.MerchantPlugMapper;
import com.aia.plug.service.MerchantPlugService;
import com.aia.user.entity.AgentEntity;
import com.aia.user.service.AgentService;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 代理人推广管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MerchantPlugServiceImpl extends ServiceImpl<MerchantPlugMapper, MerchantPlugEntity> implements MerchantPlugService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AgentService agentService;

    public static final String DOMAIN="";

    @Override
    public ApiResp<PageResp<MerchantPlugEntity>> page(MerchantPlugPageReq merchantPlugPageReq, HttpServletRequest request) {
        String token = request.getHeader("Authorization").split("_")[1];
        Long agentId = (Long) redisUtils.get(token);
        LambdaQueryWrapper<MerchantPlugEntity> queryWrap = new QueryWrapper<MerchantPlugEntity>().lambda();
        queryWrap.eq(MerchantPlugEntity::getAgentId, agentId);
        PageHelper.startPage(merchantPlugPageReq.getPageNo(), merchantPlugPageReq.getPageSize());
        Page<MerchantPlugEntity> page = (Page<MerchantPlugEntity>) list(queryWrap);
        return ApiResp.page(page);

    }

    @Override
    public ApiResp<String> add(MerchantPlugAddReq merchantPlugAddReq) {
        //判断返点
        Long agentId = SecurityFrameworkUtils.getLoginUserId();
        AgentEntity agentEntity = agentService.getById(agentId);
        if(merchantPlugAddReq.getRate()>agentEntity.getAssiRate()){
            throw new ApiBussException("this rate is larger than the max value");
        }
        MerchantPlugEntity entity=new MerchantPlugEntity();
        entity.setPlugUrl(DOMAIN+"?agentId="+agentId+"&rate="+merchantPlugAddReq.getRate());
        entity.setAgentId(agentId);
        entity.setRate(merchantPlugAddReq.getRate());
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(MerchantPlugEditReq merchantPlugEditReq) {
        //判断返点
        Long agentId = SecurityFrameworkUtils.getLoginUserId();
        AgentEntity agentEntity = agentService.getById(agentId);
        if(merchantPlugEditReq.getRate()>agentEntity.getAssiRate()){
            throw new ApiBussException("this rate is larger than the max value");
        }
        MerchantPlugEntity entity = getById(merchantPlugEditReq.getId());
        entity.setPlugUrl(DOMAIN+"?agentId="+agentId+"&rate="+merchantPlugEditReq.getRate());
        entity.setAgentId(agentId);
        entity.setRate(merchantPlugEditReq.getRate());
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }



}
