package com.aia.system.resp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年8月15日 下午2:31:34 
*/
@Data
public class CreateTaskRsp {
	@NotBlank(message = "定时任务名称不能为空")
	@ApiModelProperty(value = "定时任务名称",required = true)
	private String taskName;

	@NotBlank(message = "执行频率不能为空")
	@ApiModelProperty(value = "执行频率",required = true)
	private String cron;

	@NotBlank(message = "实例化类名不能为空")
	@ApiModelProperty(value = "实例化类名",required = true)
	private String beanName;

	@NotBlank(message = "执行方法名称不能为空")
	@ApiModelProperty(value = "执行方法名称",required = true)
	private String excuteMethod;

	@ApiModelProperty(value = "备注")
	private String remark;

	@NotNull(message = "状态不能为空")
	@ApiModelProperty(value = "状态（0启用，1禁用）")
	private int isUse;
}
