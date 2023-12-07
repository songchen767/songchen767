package com.aia.finance.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 11:27
 */
@Data
public class IncomeChannelPageReq extends BasePageReq {

    @ApiModelProperty(value = "渠道名称")
    private String name;

    @ApiModelProperty(value = "渠道类型")  //0银行卡，1支付宝，2微信，3虚拟币
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
