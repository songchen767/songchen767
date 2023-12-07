package com.aia.finance.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.personal.entity.MemberBetRecordEntity;
import com.aia.personal.req.BetRecordPageReq;
import com.aia.personal.service.MemberBetRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员投注记录表 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/personal/memberBetRecord")
public class MemberBetRecordController {


    @Autowired
    private MemberBetRecordService memberBetRecordService;

    @HasLogin
    @GetMapping("/page")
    @ApiOperation(value = "分页查询示例")
    public ApiResp<PageResp<MemberBetRecordEntity>> page(BetRecordPageReq betRecordPage) {
        return memberBetRecordService.page(betRecordPage);
    }


}
