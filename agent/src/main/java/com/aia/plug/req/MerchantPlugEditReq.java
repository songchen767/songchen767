package com.aia.plug.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 11:47
 */
@Data
public class MerchantPlugEditReq extends MerchantPlugAddReq{

    @ApiModelProperty(value="id",required = true)
    @NotNull(message="id不能为空")
    private Long id;


}
