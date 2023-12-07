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
 * 会员钱包管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_member_wallet")
public class MemberWalletEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员ID
     */
    @ApiModelProperty(value="会员ID")
    private Long memberId;

    /**
     * 余额
     */
    @ApiModelProperty(value="余额")
    private Double balance;

    /**
     * 冻结金额
     */
    @ApiModelProperty(value="冻结金额")
    private Double freezeAmount;

}
