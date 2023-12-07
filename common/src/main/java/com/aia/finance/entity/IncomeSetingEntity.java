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
 * 充值配置管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_income_seting")
public class IncomeSetingEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 渠道ID
     */
    @ApiModelProperty(value="渠道ID")
    private Long channelId;

    /**
     * 支付宝账号(所属充值渠道为1时必填)
     */
    @ApiModelProperty(value="支付宝账号")
    private String alipay;

    /**
     * 微信账号(所属充值渠道为2时必填)
     */
    @ApiModelProperty(value="微信账号")
    private String wechat;

    /**
     * 虚拟币地址(所属充值渠道为3时必填)
     */
    @ApiModelProperty(value="虚拟币地址")
    private String coin;

    /**
     * 户名(所属充值渠道为0时必填)
     */
    @ApiModelProperty(value="户名")
    private String account;

    /**
     * 银行名称(所属充值渠道为0时必填)
     */
    @ApiModelProperty(value="银行名称")
    private String bankName;

    /**
     * 银行卡号(所属充值渠道为0时必填)
     */
    @ApiModelProperty(value="银行卡号")
    private String bankCardNo;

    /**
     * 状态（0停用，1启用）
     */
    @ApiModelProperty(value="状态（0停用，1启用）")
    private Integer status;

    /**
     * 渠道类型
     */
    @ApiModelProperty(value="渠道类型")
    private Integer type;

}
