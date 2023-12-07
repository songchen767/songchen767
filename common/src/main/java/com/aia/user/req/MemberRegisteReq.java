package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 16:14
 */
@Data
public class MemberRegisteReq {

    @ApiModelProperty(value="用户名",required = true)
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value="密码",required = true)
    @NotNull(message="密码不能为空")
    private String password;

    @ApiModelProperty(value="电话",required = true)
    @NotBlank(message="电话不能为空")
    private String telPhone;

    @ApiModelProperty(value="昵称",required = false)
    private String nickName;

    @ApiModelProperty(value="真实姓名",required = true)
    @NotBlank(message="真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value="上级会员ID",required = false)
    private Long parentMemberId;

    @ApiModelProperty(value="代理ID",required = false)
    private Long agenId;
}
