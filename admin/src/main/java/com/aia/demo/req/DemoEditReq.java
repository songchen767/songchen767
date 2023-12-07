package com.aia.demo.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午5:25:06 
*/
@Data
public class DemoEditReq {
	@NotNull(message = "ID不能为空")
	@ApiModelProperty(value = "ID",required = true)
	private Long id;
	
	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名",required = true)
	private String userName;
	
	@NotNull(message = "类型不能为空")
	@ApiModelProperty(value = "类型",required = true)
	private Integer type;
}
