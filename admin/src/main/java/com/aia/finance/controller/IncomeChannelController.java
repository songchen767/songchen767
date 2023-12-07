package com.aia.finance.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.entity.IncomeChannelEntity;
import com.aia.finance.req.IncomeChannelAddReq;
import com.aia.finance.req.IncomeChannelEditReq;
import com.aia.finance.req.IncomeChannelPageReq;
import com.aia.finance.req.IncomeChannelStatusUpdateReq;
import com.aia.finance.service.IncomeChannelService;
import com.aia.system.req.AuthorityAddReq;
import com.aia.system.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api(tags = "充值渠道模块")
public class IncomeChannelController {

    @Autowired
    private IncomeChannelService incomeChannelService;

    @ApiOperation("充值渠道分页列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:query')")
    @LogOperate(operate = "查询", service = IncomeChannelService.class, type = DataOperateContants.OTHER)
    public ApiResp<PageResp<IncomeChannelEntity>> page(IncomeChannelPageReq incomeChannelPageReq) {
        return incomeChannelService.page(incomeChannelPageReq);
    }

    @PostMapping("/add")
    @ApiOperation("充值渠道新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:create')")
    @LogOperate(operate = "添加", service = IncomeChannelService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid IncomeChannelAddReq incomeChannelAddReq) {
        return incomeChannelService.add(incomeChannelAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("充值渠道修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:edit')")
    @LogOperate(operate = "修改", service = IncomeChannelService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid IncomeChannelEditReq incomeChannelEditReq) {
        return incomeChannelService.eidt(incomeChannelEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除充值渠道")
    @LogOperate(operate = "删除", service = IncomeChannelService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return incomeChannelService.del(id);
    }

    @PostMapping("/updateStatus")
    @ApiOperation("启/停渠道")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:edit')")
    @LogOperate(operate = "修改", service = IncomeChannelService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> updateStatus(@RequestBody @Valid IncomeChannelStatusUpdateReq incomeChannelStatusUpdateReq) {
        return incomeChannelService.updateStatus(incomeChannelStatusUpdateReq);
    }

    @GetMapping("/channelList")
    @ApiOperation("渠道列表")
    //@PreAuthorize("@ss.hasAnyPermissions('system:income_channel:query')")
    @LogOperate(operate = "查询", service = IncomeChannelService.class, type = DataOperateContants.OTHER)
    public ApiResp<List<IncomeChannelEntity>> channelList() {
        return incomeChannelService.channelList();
    }


}
