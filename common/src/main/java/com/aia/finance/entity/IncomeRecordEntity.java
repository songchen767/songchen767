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
 * 会员充值记录
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_income_record")
public class IncomeRecordEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员ID
     */
    @ApiModelProperty(value="会员ID")
    private Long memberId;

    /**
     * 金额
     */
    @ApiModelProperty(value="金额")
    private BigDecimal amount;

    /**
     * 充值渠道(0银行卡)
     */
    @ApiModelProperty(value="充值渠道")
    private Integer channel;

    /**
     * 收款卡号（充值渠道为0时必填）
     */
    @ApiModelProperty(value="收款卡号")
    private String cardNo;

    /**
     * 审核状态（0待审核，1审核通过，2驳回，3已取消）
     */
    @ApiModelProperty(value="审核状态（0待审核，1审核通过，2驳回，3已取消）")
    private Integer aduitStatus;

    /**
     * 审核时间
     */
    @ApiModelProperty(value="审核时间")
    private LocalDateTime aduitTime;

    /**
     * 驳回原因（审核状态为2时必填）
     */
    @ApiModelProperty(value="驳回原因")
    private String rejection;

}
