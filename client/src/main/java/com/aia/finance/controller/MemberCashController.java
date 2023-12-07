package com.aia.finance.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.finance.req.QueryReq;
import com.aia.finance.req.WithdrawalsReq;
import com.aia.finance.res.CashInfoRes;
import com.aia.personal.service.MemberCashService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 会员提现管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/personal/memberCash")
public class MemberCashController {


    @Autowired
    private MemberCashService memberCashService;


    @PostMapping("/withdrawals")
    @ApiOperation("提现")
    @HasLogin
    public ApiResp<String> withdrawals(@RequestBody @Valid WithdrawalsReq withdrawalsReq){
        return memberCashService.withdrawals(withdrawalsReq);
    }


    @PostMapping("/info")
    @ApiOperation("积分信息查询")
    @HasLogin
    public ApiResp<List<CashInfoRes>> info(@RequestBody @Valid QueryReq queryReq){
        return memberCashService.info(queryReq);
    }






}
