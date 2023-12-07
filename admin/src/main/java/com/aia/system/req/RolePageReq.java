package com.aia.system.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 15:20
 */
@Data
public class RolePageReq extends BasePageReq {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色名称")
    private String code;

    @ApiModelProperty(value = "角色状态")
    private Integer status;   //0停用  1启用
}
