package com.aia.user.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.config.JWTConfig;
import com.aia.dto.LoginDto;
import com.aia.enums.OperationEnum;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.resp.LoginResp;
import com.aia.user.entity.AgentEntity;
import com.aia.user.mapper.AgentMapper;
import com.aia.user.req.*;
import com.aia.user.service.AgentService;
import com.aia.user.service.MemberService;
import com.aia.util.JWTTokenUtil;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 代理人信息 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, AgentEntity> implements AgentService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtils redisUtils;

    private static final String BASE_URL = "";

    //根据id查询父级代理人
    public AgentEntity checkParent(Long id) {
        AgentEntity agentById = getById(id);
        return agentById;
    }

    @Override
    public ApiResp<PageResp<AgentEntity>> page(AgentPageReq agentPageReq) {
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        if (!StringUtils.isEmpty(agentPageReq.getUserName())) {
            lambdaQuery.like(AgentEntity::getUserName, agentPageReq.getUserName());
        }
        if (!ObjectUtils.isEmpty(agentPageReq.getStatus())) {
            lambdaQuery.eq(AgentEntity::getStatus, agentPageReq.getStatus());
        }
        PageHelper.startPage(agentPageReq.getPageNo(), agentPageReq.getPageSize());
        Page<AgentEntity> page = (Page<AgentEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    @Transactional
    public ApiResp<String> updateStatus(AgentStatusUpdateReq agentStatusUpdateReq) {
        AgentEntity agent = getById(agentStatusUpdateReq.getId());
        if (ObjectUtils.isEmpty(agent)) {
            throw new BusinessException("this agent is not exist");
        }
        agent.setStatus(agentStatusUpdateReq.getStatus());
        saveOrUpdate(agent);
        if (OperationEnum.RELATION.getValue() == agentStatusUpdateReq.getOperate()) {
            //冻结/解冻代理以及所有会员
            memberService.updateStatusByAgentId(agentStatusUpdateReq.getId(), agentStatusUpdateReq.getStatus());
        }
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> transferAgent(AgentTransferReq agentTransferReq) {
        AgentEntity agentTargentEntity = getById(agentTransferReq.getTargetAgentId());
        if (ObjectUtils.isEmpty(agentTargentEntity)) {
            throw new BusinessException("this target agent is not exist");
        }
        Long oldAgentId = agentTransferReq.getOldAgentId();
        List<AgentEntity> newEntity = new ArrayList<>();
        //先判断原始会员id是否为空
        if (!ObjectUtils.isEmpty(oldAgentId)) {
            List<AgentS> agentS = agentTransferReq.getAgentS();
            for (AgentS item : agentS) {
                oldAgentId = item.getParentId();
            }
            LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
            lambdaQuery.eq(AgentEntity::getParentId, oldAgentId);
            List<AgentEntity> list = list(lambdaQuery);
            for (AgentEntity item : list) {
                item.setParentId(agentTransferReq.getTargetAgentId());
                newEntity.add(item);
            }
        } else {
            List<AgentS> memberS = agentTransferReq.getAgentS();
            List<Long> memberIds = new ArrayList<>();
            for (AgentS item : memberS) {
                memberIds.add(item.getAgentId());
            }
            LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
            lambdaQuery.in(AgentEntity::getId, memberIds);
            List<AgentEntity> list = list(lambdaQuery);
            for (AgentEntity item : list) {
                item.setParentId(agentTransferReq.getTargetAgentId());
                newEntity.add(item);
            }
        }
        //判断原会员和目标会员是否为顶级,并且是否为同一个代理
        AgentEntity memberOldEntity = getById(oldAgentId);
        if (!agentTargentEntity.getParentId().equals(memberOldEntity.getParentId())) {
            throw new BusinessException("agentId not consistent");
        }
        saveOrUpdateBatch(newEntity);
        return ApiResp.sucess();
    }

    @Override
    @Transactional
    public ApiResp<String> registe(AgentRegisteReq agentRegisteReq) {
        //判断用户名是否已经存在
        if (!chaeckUserName(agentRegisteReq.getUserName())) {
            throw new BusinessException("this username is repeat");
        }
        chaecAgent(agentRegisteReq.getAgentId(),agentRegisteReq.getAssiRate());
        AgentEntity agentEntity = getById(agentRegisteReq.getAgentId());
        AgentEntity newAgentEntity = new AgentEntity();
        newAgentEntity.setParentId(agentRegisteReq.getAgentId());
        newAgentEntity.setStatus(1);
        newAgentEntity.setRealName(agentRegisteReq.getRealName());
        newAgentEntity.setUserName(agentRegisteReq.getUserName());
        String password = bCryptPasswordEncoder.encode("123456");
        newAgentEntity.setPassword(password);
        newAgentEntity.setAssiRate(agentRegisteReq.getAssiRate());
        agentEntity.setAssiRate(agentEntity.getAssiRate() - agentRegisteReq.getAssiRate());
        updateById(agentEntity);
        save(newAgentEntity);
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        lambdaQuery.eq(AgentEntity::getUserName, agentRegisteReq.getUserName());
        AgentEntity two = getOne(lambdaQuery);
        two.setMemberPlugUrl(BASE_URL + "?agentId=");
        updateById(two);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<PageResp<AgentEntity>> childAgentPage(ChildAgentPageReq childAgentPageReq) {
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        if (!StringUtils.isEmpty(childAgentPageReq.getUserName())) {
            lambdaQuery.like(AgentEntity::getUserName, childAgentPageReq.getUserName());
        }
        if (!ObjectUtils.isEmpty(childAgentPageReq.getStatus())) {
            lambdaQuery.eq(AgentEntity::getStatus, childAgentPageReq.getStatus());
        }
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        lambdaQuery.eq(AgentEntity::getParentId,loginUserId);
        PageHelper.startPage(childAgentPageReq.getPageNo(), childAgentPageReq.getPageSize());
        Page<AgentEntity> page = (Page<AgentEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    @Transactional
    public ApiResp<String> add(AgentAddReq agentAddReq) {
        if (!chaeckUserName(agentAddReq.getUserName())) {
            throw new BusinessException("this username is repeat");
        }
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        chaecAgent(loginUserId,agentAddReq.getAssiRate());
        AgentEntity parentAgent = getById(loginUserId);
        AgentEntity agentEntity=new AgentEntity();
        agentEntity.setAssiRate(agentAddReq.getAssiRate());
        agentEntity.setTelPhone(agentAddReq.getTelPhone());
        agentEntity.setUserName(agentAddReq.getUserName());
        String password = bCryptPasswordEncoder.encode("123456");
        agentEntity.setPassword(password);
        agentEntity.setRealName(agentAddReq.getRealName());
        save(agentEntity);
        parentAgent.setAssiRate(parentAgent.getAssiRate()-agentAddReq.getAssiRate());
        updateById(parentAgent);
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        lambdaQuery.eq(AgentEntity::getUserName, agentAddReq.getUserName());
        AgentEntity one = getOne(lambdaQuery);
        one.setMemberPlugUrl(BASE_URL+"?agentId="+one.getId());
        updateById(one);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<LoginResp> login(AgentLoginReq agentLoginReq) {
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        lambdaQuery.eq(AgentEntity::getUserName, agentLoginReq.getUserName());
        AgentEntity entity = getOne(lambdaQuery);
        if(ObjectUtils.isEmpty(entity)){
            throw new BusinessException("agent is not exist");
        }
        boolean matches = bCryptPasswordEncoder.matches(agentLoginReq.getPassword(),entity.getPassword());

        if(!matches){
            throw new BusinessException("The username or password is incorrect");
        }
        if(StatusEnum.OFF.getValue().equals(entity.getStatus())){
            throw new BusinessException("The user's account is frozen");
        }
        LoginDto loginDto=new LoginDto();
        loginDto.setIsAdmin(0);
        loginDto.setUserId(entity.getId());
        String accessToken = JWTTokenUtil.createAccessToken(loginDto);
        LoginResp loginResp = new LoginResp();
        loginResp.setAccessToken(accessToken);
        redisUtils.set(accessToken, entity,2*60*60);
        return ApiResp.sucess(loginResp);
    }

    @Override
    public ApiResp<String> logOut(HttpServletRequest request) {
        String authorization = request.getHeader(JWTConfig.tokenHeader);
        redisUtils.del(authorization.split("_")[1]);
        return ApiResp.sucess();
    }

    /**
     * 根据账户名判断是否存在
     */
    public boolean chaeckUserName(String userName){
        LambdaQueryWrapper<AgentEntity> lambdaQuery = new QueryWrapper<AgentEntity>().lambda();
        lambdaQuery.eq(AgentEntity::getUserName, userName);
        AgentEntity one = getOne(lambdaQuery);
        if(ObjectUtils.isEmpty(one)){
            return true;
        }
        return false;
    }

    /**
     * 上级代理信息判断
     */
    public void chaecAgent(Long id,Double assiRate){
        AgentEntity agentEntity = getById(id);
        if (ObjectUtils.isEmpty(agentEntity)) {
            throw new BusinessException("this agent is not exist");
        }
        if (StatusEnum.OFF.getValue().equals(agentEntity.getStatus())) {
            throw new BusinessException("this agent is freezed");
        }
        if (agentEntity.getAssiRate() < assiRate) {
            throw new BusinessException("this agent rate error");
        }
    }

}
