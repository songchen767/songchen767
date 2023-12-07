package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 10:17
 */
@Data
public class AgentTransferReq {

    @ApiModelProperty(value="目标代理ID")
    @NotNull(message = "目标代理不能为空")
    private Long targetAgentId;

    @ApiModelProperty(value="原代理ID")
    private Long oldAgentId;

    @ApiModelProperty(value="需要转移代理列表")
    private List<AgentS> agentS;
}
