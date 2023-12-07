package com.aia.plug.controller;

import com.aia.annotations.LogOperate;
import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.constans.DataOperateContants;
import com.aia.finance.req.CashChannelAddReq;
import com.aia.finance.req.CashChannelEditReq;
import com.aia.finance.service.CashChannelService;
import com.aia.plug.req.MerchantPlugAddReq;
import com.aia.plug.req.MerchantPlugEditReq;
import com.aia.plug.req.MerchantPlugPageReq;
import com.aia.plug.entity.MerchantPlugEntity;
import com.aia.plug.service.MerchantPlugService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 代理人推广管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/plug/merchantPlug")
public class MerchantPlugController {

    @Autowired
    private MerchantPlugService merchantPlugService;

    @ApiOperation("代理推广列表")
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_plug:query')")
    @LogOperate(operate = "查询", service = MerchantPlugService.class, type = DataOperateContants.OTHER)
    public ApiResp<PageResp<MerchantPlugEntity>> page(MerchantPlugPageReq merchantPlugPageReq, HttpServletRequest request) {
        return merchantPlugService.page(merchantPlugPageReq, request);
    }

    @PostMapping("/add")
    @ApiOperation("代理推广新增")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_plug:create')")
    @LogOperate(operate = "添加", service = MerchantPlugService.class, type = DataOperateContants.ADD)
    public ApiResp<String> add(@RequestBody @Valid MerchantPlugAddReq merchantPlugAddReq) {
        return merchantPlugService.add(merchantPlugAddReq);
    }

    @PostMapping("/edit")
    @ApiOperation("代理推广修改")
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_plug:edit')")
    @LogOperate(operate = "修改", service = MerchantPlugService.class, type = DataOperateContants.EDIT)
    public ApiResp<String> edit(@RequestBody @Valid MerchantPlugEditReq merchantPlugEditReq) {
        return merchantPlugService.eidt(merchantPlugEditReq);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "代理推广删除")
    @LogOperate(operate = "删除", service = MerchantPlugService.class, type = DataOperateContants.DEL)
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    //@PreAuthorize("@ss.hasAnyPermissions('system:agent_plug:del')")
    public ApiResp<String> del(@PathVariable("id") Long id) {
        return merchantPlugService.del(id);
    }

}
