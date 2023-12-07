package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.req.ChannelBindCashReq;
import com.aia.finance.service.AgentCashService;
import com.aia.finance.service.CashChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chentao
 * @version 创建时间: 2023-11-17 12:50
 */
@RestController
@RequestMapping("/finance/cashChannel")
@Api(tags="积分渠道管理")
public class CashChannelController {

    @Autowired
    private CashChannelService cashChannelService;

    @ApiOperation("积分渠道获取")
    @GetMapping("/getCashChannel")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash_channel:query')")
    public ApiResp<List<CashChannelEntity>> getCashChannel() {
        return cashChannelService.getCashChannel();
    }




}
