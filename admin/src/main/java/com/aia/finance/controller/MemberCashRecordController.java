package com.aia.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.MemberCashRecordEntity;
import com.aia.finance.req.MemberCashRecordPageReq;
import com.aia.finance.service.MemberCashRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 会员提现记录 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/memberCashRecord")
@Api(tags="会员提现记录")
public class MemberCashRecordController {

    @Autowired
    private MemberCashRecordService memberCashRecordService;

    @ApiOperation("会员积分分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:member_cash:query')")
    @LogOperate(operate = "查询",service = MemberCashRecordService.class,type = DataOperateContants.OTHER)
    public ApiResp<PageResp<MemberCashRecordEntity>> page(MemberCashRecordPageReq memberCashPageReq){
        return memberCashRecordService.page(memberCashPageReq);
    }

}
