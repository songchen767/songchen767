package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 14:50
 */
@Data
public class IncomeChannelEditReq {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "渠道名称", required = true)
    @NotBlank(message = "渠道名称不能为空")
    private String name;

    @ApiModelProperty(value = "渠道类型", required = true)
    @NotNull(message = "渠道类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
