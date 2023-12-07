package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-27 15:39
 */
@Data
public class AgentRegisteReq {

    @ApiModelProperty(value="代理人id",required = true)
    @NotNull(message = "代理人id不能为空")
    private Long agentId;

    @ApiModelProperty(value="可分配返点",required = true)
    @NotNull(message = "可分配返点不能为空")
    private double assiRate;

    @ApiModelProperty(value="用户名",required = true)
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value="真实姓名",required = true)
    @NotBlank(message="真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value="联系电话",required = true)
    @NotBlank(message="联系电话不能为空")
    private String telPhone;







}
