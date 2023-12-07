package com.aia.game.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 11:05
 */
@Data
public class GamerPageReq extends BasePageReq {

    @ApiModelProperty(value="游戏商名称")
    private String name;

    @ApiModelProperty(value="状态 0:停用;1:启用")
    private Integer status ;
}
