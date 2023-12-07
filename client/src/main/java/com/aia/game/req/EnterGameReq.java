package com.aia.game.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午6:08:55 
*/
@Data
public class EnterGameReq {
	@NotNull(message = "游戏商ID不能为空")
	@ApiModelProperty(value = "游戏商ID",required = true)
	private Long gamerId;

	@NotBlank(message = "游戏CODE不能为空")
	@ApiModelProperty(value = "游戏CODE",required = true)
	private String code;

	@NotBlank(message = "供应商CODE不能为空")
	@ApiModelProperty(value = "供应商CODE",required = true)
	private String providerCode;
}
