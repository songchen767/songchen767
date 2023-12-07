package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 18:20
 */
@Data
public class MemberTransReq {

    @ApiModelProperty(value="需要转移会员列表")
    private Long memberId;

    @ApiModelProperty(value="所属会员ID")
    private Long parentMemberId;
}
