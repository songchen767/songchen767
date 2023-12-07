package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.req.AgentCashRecordAddReq;
import com.aia.finance.service.AgentCashRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 16:52
 */

@RestController
@RequestMapping("/finance/agentCashRecord")
@Api(tags="积分记录管理")
public class AgentCashRecordController {

    @Autowired
    private AgentCashRecordService agentCashRecordService;

    @PostMapping("/add")
    @ApiOperation("积分记录新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_cash_record:create')")
    @LogOperate(operate = "添加", service = AgentCashRecordService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid AgentCashRecordAddReq agentCashRecordAddReq){
        return agentCashRecordService.add(agentCashRecordAddReq);
    }


}
