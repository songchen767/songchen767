package com.aia.user.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 21:30
 */

@Data
public class AgentPageReq extends BasePageReq {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "状态  0:停用;1:启用")
    private Integer status;
}
