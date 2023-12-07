package com.aia.user.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-12 22:36
 */
@Data
public class MemberPageReq extends BasePageReq {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    private Integer status;

    @ApiModelProperty(value = "代理id")
    private Long agentId;
}
