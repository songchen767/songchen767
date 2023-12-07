package com.aia.demo.req;

import com.aia.base.req.BasePageReq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午5:34:41 
*/
@Data
public class DemoPageReq extends BasePageReq{
	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "类型")
	private Integer type;
}
