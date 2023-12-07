package com.aia.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 15:06
 */
@Data
public class RoleAddReq {

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码",required = true)
    private String code;

    @ApiModelProperty(value = "备注",required = true)
    @NotBlank(message = "备注不能为空")
    private String remark;

    @NotNull(message = "角色状态不能为空")
    @ApiModelProperty(value = "角色状态",required = true)
    private Integer status;


}
