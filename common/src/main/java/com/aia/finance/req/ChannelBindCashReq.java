package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 15:49
 */
@Data
public class ChannelBindCashReq {

    @ApiModelProperty(value = "积分渠道ID", required = true)
    @NotNull(message = "积分渠道ID不能为空")
    private Long channelId;

    @ApiModelProperty(value = "银行名称", required = true)
    private String bankName;

    @ApiModelProperty(value = "积分账号", required = true)
    @NotBlank(message = "积分账号不能为空")
    private String cashAccount;


}
