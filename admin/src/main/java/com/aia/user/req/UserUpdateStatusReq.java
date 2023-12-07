package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-12 21:46
 */
@Data
public class UserUpdateStatusReq {


    @ApiModelProperty(value="id",required = true)
    @NotNull(message = "id不能为空")
    private Long userId;

    @ApiModelProperty(value="状态",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;


}
