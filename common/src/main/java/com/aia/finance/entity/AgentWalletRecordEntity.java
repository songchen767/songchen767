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
 * 代理钱包记录
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_wallet_record")
public class AgentWalletRecordEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理钱包ID
     */
    @ApiModelProperty(value="代理钱包ID")
    private Long agentWalletId;

    /**
     * 变动金额
     */
    @ApiModelProperty(value="变动金额")
    private BigDecimal changeAmout;

    /**
     * 变动前余额
     */
    @ApiModelProperty(value="变动前余额")
    private BigDecimal beforeBalance;

    /**
     * 变动后余额
     */
    @ApiModelProperty(value="变动后余额")
    private BigDecimal afterBalance;

    /**
     * 变动类型（1 返点，2提现）
     */
    @ApiModelProperty(value="变动类型")
    private Integer type;

    /**
     * 返点来源会员ID（变动类型为1时必填）
     */
    @ApiModelProperty(value="返点来源会员ID")
    private Long fromMemberId;

    /**
     * 会员投注金额（变动类型为1时必填）
     */
    @ApiModelProperty(value="会员投注金额")
    private BigDecimal memberBetAmount;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String creator;

}
