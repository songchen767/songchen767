package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 10:19
 */
@Data
public class AgentS {

    @ApiModelProperty(value="需要转移代理ID")
    private Long agentId;

    @ApiModelProperty(value="所属代理ID")
    private Long parentId;
}
