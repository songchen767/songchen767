package com.aia.system.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年8月15日 上午11:23:14 
*/
@Data
@TableName("sys_task")
public class TaskEntity extends BaseEntity{
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	
	@ApiModelProperty(value = "定时任务名称")
	private String taskName;
	
	@ApiModelProperty(value = "执行频率")
	private String cron;
	
	@ApiModelProperty(value = "实例化类名")
	private String beanName;
	
	@ApiModelProperty(value = "执行方法名称")
	private String excuteMethod;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty(value = "状态（0启用，1禁用）")
	private Integer isUse; 

	@ApiModelProperty(value = "所属系统")
	private String systemCode;
	
}
