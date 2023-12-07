package com.aia.system.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年7月31日 下午3:03:10 
*/
@Data
@TableName("sys_data_log")
public class SysLogEntity extends BaseEntity{
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	
	@ApiModelProperty(value = "操作名称")
	private String operate;
	
	@ApiModelProperty(value = "请求IP")
	private String ip;
	
	@ApiModelProperty(value = "数据变化")
	private String dataChange;
}
