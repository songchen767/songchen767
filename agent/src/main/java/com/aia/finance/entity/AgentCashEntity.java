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
 * 代理提现管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_agent_cash")
public class AgentCashEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 代理人ID
     */
    @ApiModelProperty(value="代理人ID")
    private Long agentId;

    /**
     * 提现渠道ID
     */
    @ApiModelProperty(value="提现渠道ID")
    private Long cashChannelId;

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
    @ApiModelProperty(value="")
    private String alipay;

    /**
     * 微信账号（所属提现渠道类型为2时必填）
     */
    @ApiModelProperty(value="微信账号（所属提现渠道类型为2时必填）")
    private String wechat;

    /**
     * 虚拟币地址（所属提现渠道类型为3时必填）
     */
    @ApiModelProperty(value="虚拟币地址（所属提现渠道类型为3时必填）")
    private String coin;

}
