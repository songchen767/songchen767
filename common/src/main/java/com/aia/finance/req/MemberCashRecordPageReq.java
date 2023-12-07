package com.aia.finance.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 11:20
 */
@Data
public class MemberCashRecordPageReq extends BasePageReq {

    @ApiModelProperty(value = "积分渠道id")
    private Long cashChannelId;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value="审核状态")
    private Integer aduitStatus;
}
