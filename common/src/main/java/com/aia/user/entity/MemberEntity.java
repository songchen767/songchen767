package com.aia.user.entity;

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
 * 会员信息
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("aia_member")
public class MemberEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String telPhone;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickeName;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realName;

    /**
     * 状态(0冻结；1正常)
     */
    @ApiModelProperty(value="状态(0冻结；1正常)")
    private Integer status;

    /**
     * 上级会员ID(会员发展的下级必填)
     */
    @ApiModelProperty(value="上级会员ID")
    private Long parentMemberId;

    /**
     * 所属代理ID(-1表示会员中奖注册不是通过代理注册)
     */
    @ApiModelProperty(value="所属代理ID")
    private Long agenId;

    /**
     * 推广链接（程序自动生成-注册地址+?memberId=）
     */
    @ApiModelProperty(value="推广链接")
    private String plugUrl;

}
