package com.aia.game.entity;

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
 * 游戏商信息
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_gamer_provider")
public class GamerEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 游戏商名称
     */
    @ApiModelProperty(value="游戏商名称")
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value="编码")
    private String code;

    /**
     * 状态（0停用，1启用）
     */
    @ApiModelProperty(value="状态（0停用，1启用）")
    private Integer status;

    /**
     * 充值接口地址
     */
    @ApiModelProperty(value="充值接口地址")
    private String incomeUrl;

    /**
     * 提现接口地址
     */
    @ApiModelProperty(value="提现接口地址")
    private String cashUrl;

    /**
     * 投注信息接口地址
     */
    @ApiModelProperty(value="投注信息接口地址")
    private String betUrl;

    /**
     * 游戏类型（1真人，2体育，3老虎机，棋牌，5彩票）
     */
    @ApiModelProperty(value="游戏类型（1真人，2体育，3老虎机，棋牌，5彩票）")
    private Integer type;

    private String clientId;
    private String clientSecret;
    
    @ApiModelProperty(value = "游戏商api基础地址")
    private String apiBaseUrl;
    
    @ApiModelProperty(value = "游戏商游戏连接基础地址")
    private String playBaseUrl;
}
