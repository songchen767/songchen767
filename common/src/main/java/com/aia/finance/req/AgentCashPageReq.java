package com.aia.finance.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 11:07
 */
@Data
public class AgentCashPageReq extends BasePageReq {

    @ApiModelProperty(value = "积分渠道id")
    private Long cashChannelId;

    @ApiModelProperty(value = "代理")
    private Long agentId;

    @ApiModelProperty(value="审核状态 0待审核，1审核通过，2驳回，3已取消")
    private Integer aduitStatus;
}
