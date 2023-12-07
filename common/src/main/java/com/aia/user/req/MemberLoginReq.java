package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 16:39
 */
@Data
public class MemberLoginReq {

    @ApiModelProperty(value="用户名",required = true)
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value="密码",required = true)
    @NotBlank(message="密码不能为空")
    private String password;
}
