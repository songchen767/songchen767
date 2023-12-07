package com.aia.game.req;

import com.aia.base.req.BasePageReq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午5:59:42 
*/
@Data
public class GameApiPageResp extends BasePageReq{
	@ApiModelProperty(value = "游戏类型")
	private Integer type;
	
	@ApiModelProperty(value = "游戏商ID")
	private Long gamerProviderId;
	
	@ApiModelProperty(value ="状态(0 停用，1启用)")
	private Integer status;
}
