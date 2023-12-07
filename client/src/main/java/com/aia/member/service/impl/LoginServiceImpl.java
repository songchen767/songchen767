package com.aia.member.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aia.base.resp.ApiResp;
import com.aia.config.JWTConfig;
import com.aia.dto.LoginDto;
import com.aia.enums.RedisKeyEnums;
import com.aia.exception.ApiBussException;
import com.aia.member.req.LoginReq;
import com.aia.member.service.LoginService;
import com.aia.resp.LoginResp;
import com.aia.user.entity.MemberEntity;
import com.aia.user.service.MemberService;
import com.aia.util.JWTTokenUtil;
import com.aia.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午6:59:01 
*/
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ApiResp<LoginResp> login(LoginReq loginReq) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberService.getOne(new QueryWrapper<MemberEntity>().lambda().eq(MemberEntity::getUserName, loginReq.getUserName()));
		if(ObjectUtils.isEmpty(memberEntity)) {
			throw new ApiBussException("The username or password is incorrect");
		}
		
		if(memberEntity.getStatus() == 0) {
			throw new ApiBussException("The user's account is frozen");
		}
		
		if(!bCryptPasswordEncoder.matches(loginReq.getPassword(), memberEntity.getPassword())) {
			throw new ApiBussException("The username or password is incorrect");
		}
		
		LoginDto loginDto = new LoginDto();
		loginDto.setUserId(memberEntity.getId());
		loginDto.setUserName(memberEntity.getUserName());
		loginDto.setIsAdmin(0);
		
		String createAccessToken = JWTTokenUtil.createAccessToken(loginDto);
		
		LoginResp loginResp = new LoginResp();
		loginResp.setAccessToken(createAccessToken);
		redisUtils.set(RedisKeyEnums.LOGIN_KEY.key+createAccessToken, loginDto ,2*60*60);
		return ApiResp.sucess(loginResp);
	}

	@Override
	public ApiResp<String> loginout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String tokenHeader = request.getHeader(JWTConfig.tokenHeader);
		String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
		redisUtils.del(RedisKeyEnums.LOGIN_KEY.key+token);
		return ApiResp.sucess();
	}

}
