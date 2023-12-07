package com.aia.user.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.*;
import com.aia.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags="会员信息模块")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("会员分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:member:query')")
    @LogOperate(operate = "查询",service = MemberService.class,type = DataOperateContants.OTHER)
    public ApiResp<PageResp<MemberEntity>> page(MemberPageReq memberPageReq){
        return memberService.page(memberPageReq);
    }



    @PostMapping("/updateStatus")
    @ApiOperation(value="会员冻结/解冻")
    @LogOperate(operate = "会员冻结/解冻",service = MemberService.class,type = DataOperateContants.EDIT)
    //@PreAuthorize("@ss.hasAnyPermissions('system:member:edit')")
    public ApiResp<String> updateStatus(@RequestBody @Valid MemberUpdateStatusReq memberUpdateStatusReq){
        return memberService.updateStatus(memberUpdateStatusReq);
    }


    @PostMapping("/transferMemberForAgent")
    @ApiOperation(value="所属代理转移")
    @LogOperate(operate = "所属代理转移",service = MemberService.class,type = DataOperateContants.OTHER)
    //@PreAuthorize("@ss.hasAnyPermissions('system:member:edit')")
    public ApiResp<String> transferMemberForAgent(@RequestBody @Valid MemberTransferAgentReq memberTransferAgentReq){
        return memberService.transferMember(memberTransferAgentReq);

    }

    @PostMapping("/transferMemberForParent")
    @ApiOperation(value="所属会员转移")
    @LogOperate(operate = "所属会员转移",service = MemberService.class,type = DataOperateContants.OTHER)
    //@PreAuthorize("@ss.hasAnyPermissions('system:member:edit')")
    public ApiResp<String> transferMemberForParent(@RequestBody @Valid MemberTransferParentReq memberTransferAgentReq){
        return memberService.transferMemberForParent(memberTransferAgentReq);
    }





}
