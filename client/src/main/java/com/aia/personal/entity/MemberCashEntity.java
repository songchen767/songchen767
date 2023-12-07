package com.aia.personal.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 会员提现管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_member_cash")
public class MemberCashEntity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 提现渠道ID
     */
    private Integer cashChannelId;

    /**
     * 提现银行名称（所属提现渠道类型为0时必填）
     */
    private String bankName;

    /**
     * 提现银行卡号（所属提现渠道类型为0时必填）
     */
    private String bankCardNo;

    /**
     * 支付宝账号（所属提现渠道类型为1时必填）
     */
    private String alipay;

    /**
     * 微信账号（所属提现渠道类型为2时必填）
     */
    private String wechat;

    /**
     * 虚拟币地址（所属提现渠道类型为3时必填）
     */
    private String coin;

}
