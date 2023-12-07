package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 10:27
 */
@Data
public class MemberTransferAgentReq {

    @ApiModelProperty(value = "目标代理ID")
    @NotNull(message = "target agent id can not null")
    private Long targetAgentId;

    @ApiModelProperty(value = "原代理ID")
    private Long oldAgentId;

    @ApiModelProperty(value = "需要转移会员列表")
    private List<MemberTransferReq> memberS;
}
