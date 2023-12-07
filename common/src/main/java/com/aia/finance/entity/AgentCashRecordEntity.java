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
 * 代理提现记录
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_cash_record")
public class AgentCashRecordEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理ID
     */
    @ApiModelProperty(value="代理ID")
    private Long agentId;

    /**
     * 提现渠道ID
     */
    @ApiModelProperty(value="提现渠道ID")
    private Long cashChannelId;

    /**
     * 提现银行名称（所属提现渠道类型为0时必填）
     */
    @ApiModelProperty(value="提现银行名称")
    private String bankName;

    /**
     * 提现银行卡号（所属提现渠道类型为0时必填）
     */
    @ApiModelProperty(value="提现银行卡号")
    private String bankCardNo;

    /**
     * 支付宝账号（所属提现渠道类型为1时必填）
     */
    @ApiModelProperty(value="支付宝账号")
    private String alipay;

    /**
     * 微信账号（所属提现渠道类型为2时必填）
     */
    @ApiModelProperty(value="微信账号")
    private String wechat;

    /**
     * 虚拟币地址（所属提现渠道类型为3时必填）
     */
    @ApiModelProperty(value="虚拟币地址")
    private String coin;

    /**
     * 金额
     */
    @ApiModelProperty(value="金额")
    private BigDecimal amount;

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
