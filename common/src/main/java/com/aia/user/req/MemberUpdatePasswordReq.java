package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-27 15:11
 */

@Data
public class MemberUpdatePasswordReq {

    @ApiModelProperty(value="会员id",required = true)
    @NotNull(message = "会员id不能为空")
    private Long memberId;

    @ApiModelProperty(value ="" ,required = true)
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty(value ="" ,required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value ="" ,required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
