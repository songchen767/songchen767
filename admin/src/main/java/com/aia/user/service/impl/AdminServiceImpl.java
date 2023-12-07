package com.aia.user.service.impl;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.config.JWTConfig;
import com.aia.dto.LoginDto;
import com.aia.enums.StatusEnum;
import com.aia.exception.BusinessException;
import com.aia.resp.LoginResp;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.*;
import com.aia.util.JWTTokenUtil;
import com.aia.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aia.user.entity.AdminEntity;
import com.aia.user.mapper.AdminMapper;
import com.aia.user.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 后台管理员 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ApiResp<PageResp<AdminEntity>> page(AdminPageReq adminPageRequest) {
        LambdaQueryWrapper<AdminEntity> lambdaQuery = new QueryWrapper<AdminEntity>().lambda();

        if (!StringUtils.isEmpty(adminPageRequest.getUserName())) {
            lambdaQuery.like(AdminEntity::getUserName, adminPageRequest.getUserName());
        }

        if (!ObjectUtils.isEmpty(adminPageRequest.getStatus())) {
            lambdaQuery.eq(AdminEntity::getStatus, adminPageRequest.getStatus());
        }
        PageHelper.startPage(adminPageRequest.getPageNo(), adminPageRequest.getPageSize());
        Page<AdminEntity> page = (Page<AdminEntity>) list(lambdaQuery);
        return ApiResp.page(page);
    }

    @Override
    public ApiResp<String> add(UserAddReq userAddReq) {
        //根据用户名以及手机号检查是否已经被使用   这里的检查范围包含了不启用的
        LambdaQueryWrapper<AdminEntity> lambdaQueryByUserName = new QueryWrapper<AdminEntity>().lambda();
        lambdaQueryByUserName.eq(AdminEntity::getUserName, userAddReq.getUserName());
        AdminEntity oneByName = getOne(lambdaQueryByUserName);
        if(!ObjectUtils.isEmpty(oneByName)){
           throw new BusinessException("this name has existed");
        }
        LambdaQueryWrapper<AdminEntity> lambdaQueryByTelphone = new QueryWrapper<AdminEntity>().lambda();
        lambdaQueryByTelphone.eq(AdminEntity::getTelPhone, userAddReq.getTelPhone());
        AdminEntity oneByTelphone = getOne(lambdaQueryByUserName);
        if(!ObjectUtils.isEmpty(oneByTelphone)){
            throw new BusinessException("this telphone has existed");
        }
        String password = bCryptPasswordEncoder.encode("123456");
        AdminEntity entity=new AdminEntity();
        BeanUtils.copyProperties(userAddReq,entity);
        entity.setPassword(password);
        save(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> eidt(UserEditReq userEditReq) {
        //根据用户名以及手机号检查是否已经被使用   这里的检查范围包含了不启用的
        LambdaQueryWrapper<AdminEntity> lambdaQueryByUserName = new QueryWrapper<AdminEntity>().lambda();
        lambdaQueryByUserName.eq(AdminEntity::getUserName, userEditReq.getUserName());
        lambdaQueryByUserName.ne(AdminEntity::getId,userEditReq.getId());
        AdminEntity oneByName = getOne(lambdaQueryByUserName);
        if(!ObjectUtils.isEmpty(oneByName)){
            throw new BusinessException("this name has existed");
        }
        LambdaQueryWrapper<AdminEntity> lambdaQueryByTelphone = new QueryWrapper<AdminEntity>().lambda();
        lambdaQueryByTelphone.eq(AdminEntity::getTelPhone, userEditReq.getTelPhone());
        lambdaQueryByUserName.ne(AdminEntity::getId,userEditReq.getId());
        AdminEntity oneByTelphone = getOne(lambdaQueryByUserName);
        if(!ObjectUtils.isEmpty(oneByTelphone)){
            throw new BusinessException("this telphone has existed");
        }
        AdminEntity entity=new AdminEntity();
        BeanUtils.copyProperties(userEditReq,entity);
        updateById(entity);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> del(Long id) {
        removeById(id);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<String> updateStatus(UserUpdateStatusReq userUpdateStatusReq) {
        AdminEntity entityById = getById(userUpdateStatusReq.getUserId());
        if(ObjectUtils.isEmpty(entityById)){
            throw new BusinessException("data not exist");
        }
        entityById.setStatus(userUpdateStatusReq.getStatus());
        updateById(entityById);
        return ApiResp.sucess();
    }

    @Override
    public ApiResp<LoginResp> login(MemberLoginReq memberLoginReq) {
        LambdaQueryWrapper<AdminEntity> lambdaQuery = new QueryWrapper<AdminEntity>().lambda();
        lambdaQuery.eq(AdminEntity::getUserName, memberLoginReq.getUserName());
        AdminEntity entity = getOne(lambdaQuery);
        if(ObjectUtils.isEmpty(entity)){
            throw new BusinessException("user is not exist");
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

    @Override
    public ApiResp<String> logOut(HttpServletRequest request) {
        String authorization = request.getHeader(JWTConfig.tokenHeader);
        redisUtils.del(authorization.split("_")[1]);
        return ApiResp.sucess();
    }
}
