package com.aia.finance.entity;

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
 * 充值渠道管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_income_channel")
public class IncomeChannelEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 渠道类型(0银行卡，1支付宝，2微信，3虚拟币)
     */
    @ApiModelProperty(value="渠道类型(0银行卡，1支付宝，2微信，3虚拟币)")
    private Integer type;

    /**
     * 状态(0停用，1启用）
     */
    @ApiModelProperty(value="状态(0停用，1启用）")
    private Integer status;

}
