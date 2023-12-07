package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Formatter;

/**
 * @author chentao
 * @version 创建时间: 2023-12-03 21:42
 */
@Data
public class WithdrawalsReq implements Serializable {

    @ApiModelProperty(value = "积分金额", required = true)
    @NotNull(message = "积分金额不能为空")
    private Double amount;

    @ApiModelProperty(value = "渠道id", required = true)
    @NotNull(message = "渠道id不能为空")
    private Long channel;

    @ApiModelProperty(value = "积分账号", required = true)
    @NotBlank(message = "积分账号不能为空")
    private String cashAccount;
}
