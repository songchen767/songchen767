package com.aia.plug.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-23 15:48
 */
@Data
public class MerchantPlugAddReq {

    @ApiModelProperty(value = "返点",required = true)
    @NotNull(message = "返点不可为空")
    private Double rate;
}
