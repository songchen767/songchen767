package com.aia.game.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 11:48
 */

@Data
public class GamePageReq extends BasePageReq {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "游戏商id")
    private Long gamerId;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    private Integer status;


}
