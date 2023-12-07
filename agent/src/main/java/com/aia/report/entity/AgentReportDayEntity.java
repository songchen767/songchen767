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
 * 代理日报表
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_report_day")
public class AgentReportDayEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理ID
     */
    @ApiModelProperty(value="代理ID")
    private Long agentId;

    /**
     * 日期(yyyy-mm-dd)
     */
    @ApiModelProperty(value="日期")
    private String day;

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
