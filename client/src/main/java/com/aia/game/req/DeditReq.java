package com.aia.game.req;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午8:38:20 
*/
@Data
public class DeditReq {
	@NotNull(message = "金额不能为空")
	@ApiModelProperty(value = "金额",required = true)
	private Double amt;
	
	@NotNull(message = "游戏商ID不能为空")
	@ApiModelProperty(value = "游戏商ID",required = true)
	private Long gamerId;
}
