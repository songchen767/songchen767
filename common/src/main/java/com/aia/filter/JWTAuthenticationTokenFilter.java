package com.aia.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.aia.base.resp.ApiResp;
import com.aia.config.JWTConfig;
import com.aia.dto.LoginDto;
import com.aia.enums.RedisKeyEnums;
import com.aia.util.RedisUtils;
import com.aia.util.SecurityRespUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 * @Author Sans
 * @CreateTime 2019/10/5 16:41
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
	private RedisUtils redisUtils;

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager,RedisUtils redisUtils) {
        super(authenticationManager);
        this.redisUtils = redisUtils;
    }

	@Override
	@SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//通过gateway调用接口时使用
		String userJsonStr = request.getHeader("user");
		if(!StringUtils.isEmpty(userJsonStr)) {
			LoginDto loginUser = JSONObject.toJavaObject(JSON.parseObject(userJsonStr), LoginDto.class);
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, loginUser.getUserId(), Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
    		return;
		}
		
		
		// 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.tokenHeader);
        if (null!=tokenHeader && tokenHeader.startsWith(JWTConfig.tokenPrefix)) {
        	String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
        	if(redisUtils.hasKey(RedisKeyEnums.LOGIN_KEY.key+token)) {
        		if(redisUtils.getExpire(token) <= 30*60) {//过期时间小于等于半小时自动续期
        			redisUtils.expire(token, 2*60*60);
        		}
        	}else {
        		filterChain.doFilter(request, response);
        		return;
        	}
            try {
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JWTConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String userJsonString = claims.getSubject();
                if(!StringUtils.isEmpty(userJsonString)) {
                	LoginDto loginUser = JSONObject.toJavaObject(JSON.parseObject(userJsonString), LoginDto.class);
                	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, loginUser.getUserId(), Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e){
                log.info("Token过期");
            } catch (Exception e) {
            	SecurityRespUtil.responseJson(response, ApiResp.jwtError("无效令牌"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}