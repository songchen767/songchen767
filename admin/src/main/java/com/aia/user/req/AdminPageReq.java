package com.aia.user.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-12 21:13
 */

@Data
public class AdminPageReq extends BasePageReq {


    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
