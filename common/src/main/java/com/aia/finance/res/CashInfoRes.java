package com.aia.finance.res;

import io.swagger.annotations.ApiModelProperty;

import javax.management.ValueExp;

/**
 * @author chentao
 * @version 创建时间: 2023-12-04 18:39
 */
public class CashInfoRes {

    @ApiModelProperty(value = "积分渠道id")
    private Long channeId;

    @ApiModelProperty(value = "渠道类型")
    private Integer channelType;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "积分账号")
    private String cashAccount;
}
