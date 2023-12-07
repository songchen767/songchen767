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
 * 会员钱包记录
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_member_wallet_record")
public class MemberWalletRecordEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员钱包ID
     */
    @ApiModelProperty(value="会员钱包ID")
    private Long memberWalletId;

    /**
     * 变动金额
     */
    @ApiModelProperty(value="变动金额")
    private Double changeAmout;

    /**
     * 变动前余额
     */
    @ApiModelProperty(value="变动前余额")
    private Double beforeBalance;

    /**
     * 变动后余额
     */
    @ApiModelProperty(value="变动后余额")
    private Double afterBalance;

    /**
     * 变动类型（1提现，2充值，3转出到游戏商，4从游戏商转入）
     */
    @ApiModelProperty(value="变动类型（1提现，2充值，3转出到游戏商，4从游戏商转入）")
    private Integer type;

    @ApiModelProperty(value="描述")
    private String remark;
}
