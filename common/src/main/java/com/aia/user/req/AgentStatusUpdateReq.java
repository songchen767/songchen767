package com.aia.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 22:11
 */
@Data
public class AgentStatusUpdateReq {

    @ApiModelProperty(value="代理id",required = true)
    @NotNull(message = "代理id不能为空")
    private Long id;

    @ApiModelProperty(value="状态 0:冻结;1:解冻",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value="操作类型  1:只冻结代理\n" +
            "2：冻结代理以及代理下的所有会员",required = true)
    @NotNull(message = "操作类型不能为空")
    private Integer operate;
}
