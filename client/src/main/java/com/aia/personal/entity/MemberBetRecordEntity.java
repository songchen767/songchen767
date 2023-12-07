package com.aia.personal.entity;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 会员投注记录表
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_member_bet_record")
public class MemberBetRecordEntity extends BaseEntity {

    private Long id;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 游戏商ID
     */
    private Long gamerId;

    /**
     * 游戏ID
     */
    private Long gameId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 投注金额
     */
    private BigDecimal betAmout;

    /**
     * 派奖金额
     */
    private BigDecimal awardAmount;

    /**
     * 状态
     */
    private Integer status;

}
