package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-12 21:25
 */
@Data
public class UserAddReq {

    @ApiModelProperty(value="用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value="电话",required = true)
    @NotBlank(message = "电话不能为空")
    private String telPhone;

    @ApiModelProperty(value="状态",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
