package com.aia.msg.entity;

import java.time.LocalDateTime;

import com.aia.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 公告管理
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_note")
public class NoteEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 公告内容
     */
    @ApiModelProperty(value="公告内容")
    private String content;

    /**
     * 状态(0未发布，1已发布，2撤销)
     */
    @ApiModelProperty(value="状态(0未发布，1已发布，2撤销)")
    private Integer status;

    /**
     * 公告类型ID
     */
    @ApiModelProperty(value="公告类型ID")
    private Long noteTypeId;

    /**
     * 发布时间
     */
    @ApiModelProperty(value="发布时间")
    private LocalDateTime publishTime;

    /**
     * 发布人
     */
    @ApiModelProperty(value="发布人")
    private String publisher;

    /**
     * 撤销时间
     */
    @ApiModelProperty(value="撤销时间")
    private LocalDateTime cancelTime;

    /**
     * 撤销人
     */
    @ApiModelProperty(value="撤销人")
    private String canceler;

}
