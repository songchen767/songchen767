package com.aia.user.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-27 17:43
 */
@Data
public class ChildAgentPageReq extends BasePageReq{

    @ApiModelProperty(value="代理用户名")
    private String userName;

    @ApiModelProperty(value="状态 0,冻结，1正常")
    private Integer status;



}
