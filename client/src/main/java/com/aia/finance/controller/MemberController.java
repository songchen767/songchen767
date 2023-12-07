package com.aia.finance.controller;

import com.aia.base.resp.ApiResp;
import com.aia.resp.LoginResp;
import com.aia.user.req.MemberLoginReq;
import com.aia.user.req.MemberRegisteReq;
import com.aia.user.req.MemberUpdatePasswordReq;
import com.aia.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 * 会员信息 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/user/member")
@Api(tags = "会员信息模块")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @PostMapping("/registe")
    @ApiOperation("注册")
    public ApiResp<String> registe(@RequestBody @Valid MemberRegisteReq memberRegisteReq){
       return memberService.registe(memberRegisteReq);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public ApiResp<LoginResp> login(@RequestBody @Valid MemberLoginReq memberLoginReq){
        return memberService.login(memberLoginReq);
    }

    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    public ApiResp<String> updatePassword(@RequestBody @Valid MemberUpdatePasswordReq memberUpdatePasswordReq){
        return memberService.updatePassword(memberUpdatePasswordReq);
    }
}
