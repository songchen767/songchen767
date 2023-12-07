package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 16:41
 */

@Data
public class IncomeChannelStatusUpdateReq {

    @ApiModelProperty(value="渠道id",required = true)
    @NotNull(message="渠道id不能为空")
    private Long channelId;

    @ApiModelProperty(value="状态",required = true)
    @NotNull(message="状态不能为空")
    private Integer status;
}
