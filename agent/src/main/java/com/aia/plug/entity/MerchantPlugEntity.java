package com.aia.plug.entity;

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
 * 代理人推广管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("merchant_plug")
public class MerchantPlugEntity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理人ID
     */
    @ApiModelProperty(value = "代理人ID")
    private Long agentId;

    /**
     * 推广链接（代理注册地址?agentId=&rate=）
     */
    @ApiModelProperty(value = "推广链接")
    private String plugUrl;

    /**
     * 返点
     */
    @ApiModelProperty(value = "返点")
    private Double rate;

}
