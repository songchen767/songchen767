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
 * 游戏信息
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_game")
public class GameEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 游戏名称
     */
    @ApiModelProperty(value="游戏名称")
    private String name;

    /**
     * 游戏状态（0停用，1启用）
     */
    @ApiModelProperty(value="游戏状态（0停用，1启用,2游戏商维护中）")
    private Integer status;

    /**
     * 第三方ID（在游戏商中的ID）
     */
    @ApiModelProperty(value="第三方ID")
    private String threeId;

    /**
     * 游戏商ID
     */
    @ApiModelProperty(value="游戏商ID")
    private Long gamerId;

    /**
     * 游戏类型（0未分类游戏，1老虎机)
     */
    @ApiModelProperty(value="游戏类型")
    private Integer type;

    private String providerCode;

    private String code;
}
