package com.aia.msg.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 18:21
 */

@Data
public class NoteTypePageReq extends BasePageReq {

    @ApiModelProperty(value="公告类型名称")
    private String name;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    private Integer status;
}
