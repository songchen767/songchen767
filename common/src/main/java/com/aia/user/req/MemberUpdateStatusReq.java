package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-12 22:53
 */
@Data
public class MemberUpdateStatusReq {

    @ApiModelProperty(value="会员id",required = true)
    @NotNull(message = "会员id不能为空")
    private Long memberId;

    @ApiModelProperty(value="状态 0:停用;1:启用",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
