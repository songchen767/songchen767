package com.aia.finance.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.finance.entity.MemberWalletRecordEntity;
import com.aia.finance.req.MemberWalletRecordPageReq;
import com.aia.finance.service.MemberWalletRecordService;
import com.aia.personal.entity.MemberBetRecordEntity;
import com.aia.personal.req.BetRecordPageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 会员钱包记录 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/personal/memberWalletRecord")
@Api(tags = "会员钱包记录")
public class MemberWalletRecordController {

    @Autowired
    private MemberWalletRecordService memberWalletRecordService;


    @HasLogin
    @GetMapping("page")
    @ApiOperation("钱包记录")
    public ApiResp<PageResp<MemberWalletRecordEntity>> page(MemberWalletRecordPageReq memberWalletRecordPageReq){
        return memberWalletRecordService.page(memberWalletRecordPageReq);
    }



}
