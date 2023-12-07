package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 18:01
 */

@Data
public class IncomeSettingUpdateStatusReq {

    @ApiModelProperty(value = "充值配置ID", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
