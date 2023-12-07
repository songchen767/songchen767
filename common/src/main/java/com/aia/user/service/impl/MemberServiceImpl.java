package com.aia.user.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.dto.LoginDto;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.resp.LoginResp;
import com.aia.user.entity.AgentEntity;
import com.aia.user.entity.MemberEntity;
import com.aia.user.mapper.MemberMapper;
import com.aia.user.req.*;
import com.aia.user.service.AgentService;
import com.aia.user.service.MemberService;
import com.aia.util.JWTTokenUtil;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.IFill;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员信息 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

    @Autowired
    private AgentService agentService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public ApiResp<PageResp<MemberEntity>> page(MemberPageReq memberPageReq) {
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        if (!StringUtils.isEmpty(memberPageReq.getUserName())) {
            lambdaQuery.like(MemberEntity::getUserName, memberPageReq.getUserName());
        }
        if (!ObjectUtils.isEmpty(memberPageReq.getStatus())) {
            lambdaQuery.eq(MemberEntity::getStatus, memberPageReq.getStatus());
        }
        if (!ObjectUtils.isEmpty(memberPageReq.getAgentId())) {
            lambdaQuery.like(MemberEntity::getAgenId, memberPageReq.getAgentId());
        }
        PageHelper.startPage(memberPageReq.getPageNo(), memberPageReq.getPageSize());
        Page<MemberEntity> page = (Page<MemberEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }


    @Override
    public ApiResp<String> updateStatus(MemberUpdateStatusReq memberUpdateStatusReq) {
        MemberEntity entityById = getById(memberUpdateStatusReq.getMemberId());
        if (ObjectUtils.isEmpty(entityById)) {
            throw new BusinessException("data not exist");
        }
        entityById.setStatus(memberUpdateStatusReq.getStatus());
        updateById(entityById);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> transferMember(MemberTransferAgentReq memberTransferAgentReq) {
        Long targentAgentId = memberTransferAgentReq.getTargetAgentId();
        List<MemberEntity> newMemberList=new ArrayList<>();
        AgentEntity agentEntity = agentService.checkParent(targentAgentId);
        //判断原所属代理id不为空的情况
        if (!ObjectUtils.isEmpty(memberTransferAgentReq.getOldAgentId())) {
            //如果不为空则全部转移
            LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
            lambdaQuery.eq(MemberEntity::getAgenId, memberTransferAgentReq.getOldAgentId());
            List<MemberEntity> memberList = list(lambdaQuery);
            if (CollectionUtils.isEmpty(memberList)) {
                throw new BusinessException("not member can transfer");
            }

            AgentEntity agentEntityByOld = agentService.checkParent(memberTransferAgentReq.getOldAgentId());
            if(ObjectUtils.isEmpty(agentEntity.getParentId()) && !agentEntityByOld.getParentId().equals(agentEntity.getParentId())){
                throw new BusinessException("parent agentId not consistent");
            }
            for (MemberEntity item:memberList) {
                item.setAgenId(targentAgentId);
                newMemberList.add(item);
            }
            saveOrUpdateBatch(newMemberList);
            return ApiResp.sucess();
        }
        //如果为空,则需要进入集合对象中进一步查询
        List<MemberTransferReq> memberS = memberTransferAgentReq.getMemberS();
        Long oldAgentId=null;
        for (MemberTransferReq item : memberS) {
             oldAgentId=item.getAgentId();
             break;
        }
        AgentEntity agentEntityByOld = agentService.checkParent(oldAgentId);
        if(ObjectUtils.isEmpty(agentEntity.getParentId()) && !agentEntityByOld.getParentId().equals(agentEntity.getParentId())){
            throw new BusinessException("parent agentId not consistent");
        }
        List<Long> memberIds=new ArrayList<>();
        for (MemberTransferReq item:memberS) {
            memberIds.add(item.getMemberId());
        }
        //查询出所有需要转的会员
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        lambdaQuery.in(MemberEntity::getId, memberIds);
        List<MemberEntity> list = list(lambdaQuery);
        for(MemberEntity item:list){
            item.setAgenId(targentAgentId);
            newMemberList.add(item);
        }
        saveOrUpdateBatch(newMemberList);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> transferMemberForParent(MemberTransferParentReq memberTransferAgentReq) {
        MemberEntity memberTargetEntity=getById(memberTransferAgentReq.getTargetMemberId());
        Long oldMemberId = memberTransferAgentReq.getOldMemberId();
        List<MemberEntity> newEntity=new ArrayList<>();
        //先判断原始会员id是否为空
        if(!ObjectUtils.isEmpty(oldMemberId)) {
            List<MemberTransReq> memberS = memberTransferAgentReq.getMemberS();
            for (MemberTransReq item : memberS) {
                oldMemberId = item.getParentMemberId();
            }
            LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
            lambdaQuery.eq(MemberEntity::getParentMemberId, oldMemberId);
            List<MemberEntity> list = list(lambdaQuery);
            for (MemberEntity item:list) {
                item.setParentMemberId(memberTransferAgentReq.getTargetMemberId());
                newEntity.add(item);
            }
        }else{
            List<MemberTransReq> memberS = memberTransferAgentReq.getMemberS();
            List<Long> memberIds=new ArrayList<>();
            for(MemberTransReq item:memberS){
                memberIds.add(item.getMemberId());
            }
            LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
            lambdaQuery.in(MemberEntity::getId, memberIds);
            List<MemberEntity> list = list(lambdaQuery);
            for (MemberEntity item:list){
                item.setParentMemberId(memberTransferAgentReq.getTargetMemberId());
                newEntity.add(item);
            }
        }
        //判断原会员和目标会员是否为顶级,并且是否为同一个代理
        MemberEntity memberOldEntity=getById(oldMemberId);
        if(!memberTargetEntity.getAgenId().equals(memberOldEntity.getAgenId())){
            throw new BusinessException("agentId not consistent");
        }
        saveOrUpdateBatch(newEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatusByAgentId(Long agentId,Integer status) {
        /*LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        lambdaQuery.eq(MemberEntity::getAgenId, agentId);
        List<MemberEntity> list = list(lambdaQuery);
        List<MemberEntity> newList=new ArrayList<>();
        for (MemberEntity item:list) {
            item.setStatus(status);
            newList.add(item);
        }
        saveOrUpdateBatch(newList);*/
        QueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>();
        lambdaQuery.eq("agentId", agentId);
        MemberEntity entity=new MemberEntity();
        entity.setStatus(status);
        baseMapper.update(entity,lambdaQuery);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> registe(MemberRegisteReq memberRegisteReq) {
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        lambdaQuery.eq(MemberEntity::getUserName, memberRegisteReq.getUserName());
        MemberEntity entity = getOne(lambdaQuery);
        if(!ObjectUtils.isEmpty(entity)){
            throw new BusinessException("this userName is repeat");
        }
        MemberEntity memberEntity=new MemberEntity();
        String password = bCryptPasswordEncoder.encode(memberRegisteReq.getPassword());
        BeanUtils.copyProperties(memberRegisteReq,memberEntity);
        memberEntity.setPassword(password);
        save(memberEntity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<PageResp<MemberEntity>> childMemberPage(MemberForAgentPageReq memberForAgentPageReq) {
        Long agentId= SecurityFrameworkUtils.getLoginUserId();
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        if (!StringUtils.isEmpty(memberForAgentPageReq.getMemberId())) {
            lambdaQuery.like(MemberEntity::getParentMemberId, memberForAgentPageReq.getMemberId());
        }
        if (!ObjectUtils.isEmpty(memberForAgentPageReq.getStatus())) {
            lambdaQuery.eq(MemberEntity::getStatus, memberForAgentPageReq.getStatus());
        }
        lambdaQuery.eq(MemberEntity::getAgenId,agentId);
        PageHelper.startPage(memberForAgentPageReq.getPageNo(), memberForAgentPageReq.getPageSize());
        Page<MemberEntity> page = (Page<MemberEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> updatePassword(MemberUpdatePasswordReq memberUpdatePasswordReq) {
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        lambdaQuery.eq(MemberEntity::getUserName, memberUpdatePasswordReq.getAccount());
        MemberEntity entity = getOne(lambdaQuery);
        if(ObjectUtils.isEmpty(entity)){
            throw new BusinessException("The user is not exist");
        }
        boolean matches = bCryptPasswordEncoder.matches(memberUpdatePasswordReq.getOldPassword(), entity.getPassword());
        if(!matches){
            throw new BusinessException("The username or password is incorrect");
        }
        if(StatusEnum.OFF.getValue().equals(entity.getStatus())){
            throw new BusinessException("The user's account is frozen");
        }
        String password = bCryptPasswordEncoder.encode(memberUpdatePasswordReq.getNewPassword());
        entity.setPassword(password);
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<LoginResp> login(MemberLoginReq memberLoginReq) {
        LambdaQueryWrapper<MemberEntity> lambdaQuery = new QueryWrapper<MemberEntity>().lambda();
        lambdaQuery.eq(MemberEntity::getUserName, memberLoginReq.getUserName());
        MemberEntity entity = getOne(lambdaQuery);
        if(ObjectUtils.isEmpty(entity)){
            throw new BusinessException("agent is not exist");
        }
        boolean matches = bCryptPasswordEncoder.matches(memberLoginReq.getPassword(),entity.getPassword());

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
}
