package com.aia.system.req;


import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间：2023年11月9日 下午16:30:41
 */
@Data
public class AuthorityPageReq extends BasePageReq {

    @ApiModelProperty(value = "权限名称")
    private String authorityName;

    @ApiModelProperty(value = "权限编码")
    private String code;

    @ApiModelProperty(value = "权限状态")
    private Integer status;

    @ApiModelProperty(value = "权限类型")
    private Integer type;
}
