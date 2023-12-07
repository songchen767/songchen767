package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.IncomeSetingEntity;
import com.aia.finance.req.IncomeSettingAddReq;
import com.aia.finance.req.IncomeSettingEditReq;
import com.aia.finance.req.IncomeSettingPageReq;
import com.aia.finance.req.IncomeSettingUpdateStatusReq;
import com.aia.finance.service.IncomeSetingService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 充值配置管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/finance/incomeSeting")
public class IncomeSetingController {

    @Autowired
    private IncomeSetingService incomeSetingService;

    @ApiOperation("充值配置分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:query')")
    @LogOperate(operate = "查询", service = IncomeSetingService.class, type = DataOperateContants.OTHER)
    public ApiResp<PageResp<IncomeSetingEntity>> page(IncomeSettingPageReq incomeSettingPageReq) {
        return incomeSetingService.page(incomeSettingPageReq);
    }

    @PostMapping("/add")
    @ApiOperation("充值配置新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:create')")
    @LogOperate(operate = "添加", service = IncomeSetingService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid IncomeSettingAddReq incomeSettingAddReq) {
        return incomeSetingService.add(incomeSettingAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("充值配置修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:edit')")
    @LogOperate(operate = "修改", service = IncomeSetingService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid IncomeSettingEditReq incomeSettingEditReq) {
        return incomeSetingService.eidt(incomeSettingEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除充值配置")
    @LogOperate(operate = "删除", service = IncomeSetingService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return incomeSetingService.del(id);
    }

    @GetMapping("/query/{channelId}")
    @ApiOperation(value = "根据充值渠道查询充值配置列表")
    @LogOperate(operate = "查询", service = IncomeSetingService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "channelId", value = "channelId", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:del')")
    public ApiResp<List<IncomeSetingEntity>> queryByChannelId(@PathVariable("channelId") Long channelId) {
        return incomeSetingService.queryByChannelId(channelId);
    }

    @PostMapping("/updateStatus")
    @ApiOperation("更新渠道启停状态")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_setting:edit')")
    @LogOperate(operate = "修改", service = IncomeSetingService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid IncomeSettingUpdateStatusReq incomeSettingUpdateStatusReq) {
        return incomeSetingService.updateStatus(incomeSettingUpdateStatusReq);
    }


}