package com.aia.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-09 17:11
 */
@Data
public class AuthorithEditReq {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID",required = true)
    private Long id;

    @NotBlank(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称",required = true)
    private String authorityName;

    @NotBlank(message = "权限编码不能为空")
    @ApiModelProperty(value = "权限编码",required = true)
    private String code;

    @NotNull(message = "权限状态不能为空")
    @ApiModelProperty(value = "权限状态",required = true)
    private Integer status;

    @NotNull(message = "权限类型不能为空")
    @ApiModelProperty(value = "权限类型 0:停用  1:启用",required = true)
    private Integer type;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

    @ApiModelProperty(value = "父节点id",required = false)
    private Long parentId;


}
