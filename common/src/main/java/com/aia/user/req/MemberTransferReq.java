package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 10:29
 */
@Data
public class MemberTransferReq {

    @ApiModelProperty(value="会员id")
    private Long memberId;

    @ApiModelProperty(value="所属代理id")
    private Long agentId;
}
