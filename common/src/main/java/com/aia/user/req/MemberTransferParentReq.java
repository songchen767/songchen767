package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 18:14
 */
@Data
public class MemberTransferParentReq {

    @ApiModelProperty(value = "目标会员id")
    @NotNull(message = "目标会员不为空")
    private Long targetMemberId;

    @ApiModelProperty(value = "原始会员id")
    private Long oldMemberId;

    @ApiModelProperty(value = "需要转移会员列表")
    private List<MemberTransReq> memberS;
}
