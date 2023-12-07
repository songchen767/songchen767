package com.aia.member.req;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年11月26日 下午6:59:48 
*/
@Data
public class LoginReq {
	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名",required = true)
	private String userName;

	@NotBlank(message = "密码不能为空")
	@ApiModelProperty(value = "密码",required = true)
	private String password;
}
