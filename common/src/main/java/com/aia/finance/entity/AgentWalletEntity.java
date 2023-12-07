package com.aia.finance.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 代理钱包管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_wallet")
public class AgentWalletEntity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理ID
     */
    @ApiModelProperty(value="代理ID")
    private Long agentId;

    /**
     * 余额
     */
    @ApiModelProperty(value="余额")
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    @ApiModelProperty(value="冻结金额")
    private BigDecimal freezeAmount;

}
