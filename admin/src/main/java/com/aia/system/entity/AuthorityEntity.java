package com.aia.system.entity;

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
 * 后台权限
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_authority")
public class AuthorityEntity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String authorityName;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态(0停用；1启用)
     */
    @ApiModelProperty(value = "状态(0停用；1启用)")
    private Integer status;

    /**
     * 权限类型（1菜单，2按钮，3功能）
     */
    @ApiModelProperty(value = "权限类型（1菜单，2按钮，3功能）")
    private Integer type;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

}
