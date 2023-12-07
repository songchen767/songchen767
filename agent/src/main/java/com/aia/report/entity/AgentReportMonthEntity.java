package com.aia.report.entity;

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
 * 代理月报表
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_report_month")
public class AgentReportMonthEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理ID
     */
    @ApiModelProperty(value="代理ID")
    private Long agentId;

    /**
     * 月份（yyyy-mm）
     */
    @ApiModelProperty(value="月份")
    private String month;

    /**
     * 会员投注金额
     */
    @ApiModelProperty(value="会员投注金额")
    private BigDecimal betAmount;

    /**
     * 会员派奖金额
     */
    @ApiModelProperty(value="会员派奖金额")
    private BigDecimal awardAmount;

    /**
     * 会员提现金额
     */
    @ApiModelProperty(value="会员提现金额")
    private BigDecimal memberCashAmount;

    /**
     * 会员充值金额
     */
    @ApiModelProperty(value="会员充值金额")
    private BigDecimal incomeAmount;

    /**
     * 返点金额
     */
    @ApiModelProperty(value="返点金额")
    private BigDecimal rebateAmount;

    /**
     * 下级代理提现金额
     */
    @ApiModelProperty(value="下级代理提现金额")
    private BigDecimal agentCashAmount;

    /**
     * 自己提现金额
     */
    @ApiModelProperty(value="自己提现金额")
    private BigDecimal selfCashAmount;

}
