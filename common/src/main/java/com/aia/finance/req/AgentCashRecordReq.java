package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 15:09
 */
@Data
public class AgentCashRecordReq {

    /**
     * 代理ID
     */
    @ApiModelProperty(value="代理ID",required = true)
    private Long agentId;

    /**
     * 提现渠道ID
     */
    @ApiModelProperty(value="提现渠道ID",required = true)
    private Integer cashChannelId;

    /**
     * 提现银行名称（所属提现渠道类型为0时必填）
     */
    @ApiModelProperty(value="提现银行名称（所属提现渠道类型为0时必填）")
    private String bankName;

    /**
     * 提现银行卡号（所属提现渠道类型为0时必填）
     */
    @ApiModelProperty(value="提现银行卡号（所属提现渠道类型为0时必填）")
    private String bankCardNo;

    /**
     * 支付宝账号（所属提现渠道类型为1时必填）
     */
    @ApiModelProperty(value="支付宝账号（所属提现渠道类型为1时必填）",required = true)
    private String alipay;

    /**
     * 微信账号（所属提现渠道类型为2时必填）
     */
    @ApiModelProperty(value="微信账号（所属提现渠道类型为2时必填）",required = true)
    private String wechat;

    /**
     * 虚拟币地址（所属提现渠道类型为3时必填）
     */
    @ApiModelProperty(value="虚拟币地址（所属提现渠道类型为3时必填）",required = true)
    private String coin;

    /**
     * 金额
     */
    @ApiModelProperty(value="金额",required = true)
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
    @ApiModelProperty(value="驳回原因（审核状态为2时必填）")
    private String rejection;


}
