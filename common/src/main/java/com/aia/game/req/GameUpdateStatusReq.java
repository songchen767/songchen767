package com.aia.game.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 12:02
 */
@Data
public class GameUpdateStatusReq {

    @ApiModelProperty(value = "游戏id", required = true)
    @NotNull(message = "游戏id不能为空")
    private Long gameId;

    @ApiModelProperty(value = "状态 0:停用;1:启用", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
