package com.aia.user.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.game.entity.GameEntity;
import com.aia.game.req.GamePageReq;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.MemberForAgentPageReq;
import com.aia.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

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
@Api(tags="会员信息")
public class MemberController {

    @Autowired
    private MemberService memberService;


    //@HasLogin
    @ApiOperation("下级会员分页列表")
    @GetMapping("/childMemberPage")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_member:query')")
    public ApiResp<PageResp<MemberEntity>> childMemberPage(MemberForAgentPageReq memberForAgentPageReq) {
        return memberService.childMemberPage(memberForAgentPageReq);
    }



}
