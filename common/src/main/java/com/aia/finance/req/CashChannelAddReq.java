package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 18:23
 */
@Data
public class CashChannelAddReq {

    @ApiModelProperty(value="名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value="渠道类型 0:银行卡，1:支付宝，2:微信，3:虚拟币")
    @NotNull(message = "渠道类型不能为空")
    private Integer type;

    @ApiModelProperty(value="状态 0:停用;1:启用")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
