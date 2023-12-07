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
 * 平台日报表
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_platform_report_day")
public class PlatformReportDayEntity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日期(yyyy-mm-dd)
     */
    @ApiModelProperty(value = "日期")
    private String day;

    /**
     * 投注金额
     */
    @ApiModelProperty(value = "投注金额")
    private BigDecimal betAmount;

    /**
     * 派奖金额
     */
    @ApiModelProperty(value = "派奖金额")
    private BigDecimal awardAmount;

    /**
     * 提现金额
     */
    @ApiModelProperty(value = "提现金额")
    private BigDecimal cashAmount;

    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额")
    private BigDecimal incomeAmount;

    /**
     * 盈利金额（投注金额-派奖金额 负数时表示亏损）
     */
    @ApiModelProperty(value = "盈利金额（投注金额-派奖金额 负数时表示亏损）")
    private BigDecimal gainAmount;

}
