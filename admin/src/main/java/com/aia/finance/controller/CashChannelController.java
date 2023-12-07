package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.CashChannelEntity;
import com.aia.finance.req.*;
import com.aia.finance.service.CashChannelService;
import com.aia.finance.service.IncomeChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
@Api(tags = "积分渠道模块")
public class CashChannelController {

    @Autowired
    private CashChannelService cashChannelService;

    @ApiOperation("积分渠道分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:cash_channel:query')")
    @LogOperate(operate = "查询", service = CashChannelService.class, type = DataOperateContants.OTHER)
    public ApiResp<PageResp<CashChannelEntity>> page(CashChannelPageReq cashChannelPageReq) {
        return cashChannelService.page(cashChannelPageReq);
    }

    @PostMapping("/add")
    @ApiOperation("积分渠道新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:cash_channel:create')")
    @LogOperate(operate = "添加", service = CashChannelService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid CashChannelAddReq cashChannelAddReq) {
        return cashChannelService.add(cashChannelAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("积分渠道修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:cash_channel:edit')")
    @LogOperate(operate = "修改", service = CashChannelService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid CashChannelEditReq cashChannelEditReq) {
        return cashChannelService.eidt(cashChannelEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除积分渠道")
    @LogOperate(operate = "删除", service = CashChannelService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:cash_channel:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return cashChannelService.del(id);
    }


    @PostMapping("/updateStatus")
    @ApiOperation("启/停渠道")
    //@PreAuthorize("@ss.hasAnyPermissions('system:cash_channel:edit')")
    @LogOperate(operate = "修改", service = CashChannelService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid CashChannelStatusUpdateReq cashChannelStatusUpdateReq) {
        return cashChannelService.updateStatus(cashChannelStatusUpdateReq);
    }

}
