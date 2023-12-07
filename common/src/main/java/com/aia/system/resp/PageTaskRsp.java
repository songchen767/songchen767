package com.aia.system.resp;

import com.aia.base.req.BasePageReq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年8月15日 下午3:20:40 
*/
@Data
public class PageTaskRsp extends BasePageReq{
	@ApiModelProperty(value = "定时任务名称")
	private String taskName;

	@ApiModelProperty(value = "状态（0启用，1禁用）")
	private Integer isUse;
}
