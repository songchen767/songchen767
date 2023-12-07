package com.aia.demo.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/** 
* @author yangxy
* @version 创建时间：2023年10月24日 下午4:39:42 
*/
@Data
@TableName("demo")
public class Demo extends BaseEntity{
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	
	private String userName;
	
	private Integer type;
}
