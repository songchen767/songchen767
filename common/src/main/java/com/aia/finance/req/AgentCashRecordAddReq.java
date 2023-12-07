package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 14:48
 */
@Data
public class AgentCashRecordAddReq {

    @ApiModelProperty(value="积分金额",required = true)
    @NotNull(message = "积分金额不能为空")
    private BigDecimal admount;

    @ApiModelProperty(value="积分渠道ID",required = true)
    @NotNull(message = "积分渠道ID不能为空")
    private Long channelId;

    @ApiModelProperty(value="积分账号",required = true)
    @NotBlank(message = "积分账号不能为空")
    private String cashAccount;

    @ApiModelProperty(value="银行名称")
    private String bankName;
}
