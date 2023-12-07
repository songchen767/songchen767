package com.aia.game.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 11:57
 */
@Data
public class GameAddReq {

    @ApiModelProperty(value = "名称")
    @NotBlank(message="名称不能为空")
    private String name;

    @ApiModelProperty(value = "游戏商id")
    @NotNull(message="游戏商id不能为空")
    private Long gamerId;

    @ApiModelProperty(value = "类型")
    @NotNull(message="类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    @NotNull(message="状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "第三方ID")
    @NotBlank(message="第三方ID不能为空")
    private String threeId;
}
