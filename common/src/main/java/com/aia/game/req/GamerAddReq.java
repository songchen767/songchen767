package com.aia.game.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author chentao
 * @version 创建时间: 2023-11-15 11:06
 */
@Data
public class GamerAddReq {

    @ApiModelProperty(value = "游戏商名称",required = true)
    @NotBlank(message = "游戏商名称不能为空")
    private String name;

    @ApiModelProperty(value = "充值接口地址",required = true)
    @NotBlank(message = "充值不能为空")
    private String incomeUrl;

    @ApiModelProperty(value = "积分接口地址",required = true)
    @NotBlank(message = "积分地址不能为空")
    private String cashUrl;

    @ApiModelProperty(value = "投注信息接口地址",required = true)
    @NotBlank(message = "投注信息地址不能为空")
    private String betUrl;

    @ApiModelProperty(value = "状态 0:停用;1:启用",required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "类型 1真人，2体育，3老虎机，4棋牌，5彩票",required = true)
    @NotNull(message = "类型不能为空")
    private Integer type;


}
