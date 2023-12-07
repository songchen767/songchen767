package com.aia.finance.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.service.CashChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 提现渠道管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/cashChannel")
@Api(tags="积分渠道")
public class CashChannelController {


    @Autowired
    private CashChannelService cashChannelService;


    @HasLogin
    @ApiOperation("积分渠道获取")
    @GetMapping("/getCashChannel")
    public ApiResp<List<CashChannelEntity>> getCashChannel() {
        return cashChannelService.getCashChannel();
    }

}
