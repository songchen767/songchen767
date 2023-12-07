package com.aia.user.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-17 10:54
 */

@Data
public class MemberForAgentPageReq extends BasePageReq {

    @ApiModelProperty(value = "用户名")
    private Long memberId;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    private Integer status;

}
