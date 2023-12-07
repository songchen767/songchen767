package com.aia.finance.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.service.IncomeChannelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 充值渠道管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/incomeChannel")
public class IncomeChannelController {

    @Autowired
    private IncomeChannelService incomeChannelService;

    @HasLogin
    @ApiOperation("充值渠道获取")
    @GetMapping("/getIncomeChannel")
    public ApiResp<List<IncomeChannelEntity>> getIncomeChannel() {
        return incomeChannelService.channelList();
    }


}
