package com.aia.finance.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 17:22
 */
@Data
public class IncomeSettingPageReq extends BasePageReq {

    @ApiModelProperty(value = "渠道id")
    private Long channelId;

    @ApiModelProperty(value="类型")   //0银行卡，1支付宝，2微信，3虚拟币
    private Integer type;
}
