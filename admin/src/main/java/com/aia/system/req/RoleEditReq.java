package com.aia.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 15:19
 */
@Data
public class RoleEditReq {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID",required = true)
    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码",required = true)
    private String code;

    @NotNull(message = "角色状态不能为空")
    @ApiModelProperty(value = "权限状态",required = true)
    private Integer status;

    @ApiModelProperty(value = "备注",required = true)
    @NotNull(message = "角色备注不能为空")
    private String remark;

}
