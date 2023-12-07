package com.aia.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 17:00
 */
@Data
public class RoleAuthAddReq {

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty(value = "权限id集合", required = true)
    private List<Long> authorityIds;
}
