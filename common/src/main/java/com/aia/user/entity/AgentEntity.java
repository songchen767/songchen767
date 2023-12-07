package com.aia.user.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 代理人信息
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent")
public class AgentEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realName;

    /**
     * 状态（0,冻结，1正常）
     */
    @ApiModelProperty(value="状态（0,冻结，1正常）")
    private Integer status;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String telPhone;

    /**
     * 可分配返点
     */
    @ApiModelProperty(value="可分配返点")
    private Double assiRate;

    /**
     * 上级代理ID
     */
    @ApiModelProperty(value="上级代理ID")
    private Long parentId;

    /**
     * 上级返点
     */
    @ApiModelProperty(value="上级返点")
    private Double parentRate;

    /**
     * 会员注册推广地址(系统自动生成-会员注册地址?merchantId=)
     */
    @ApiModelProperty(value="会员注册推广地址")
    private String memberPlugUrl;

}
