package com.aia.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 16:49
 */
@Data
public class RoleStatusEditReq {

    @ApiModelProperty(value = "角色状态", required = true)
    @NotNull(message = "角色状态不能为空")
    private Integer status;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Long id;
}
