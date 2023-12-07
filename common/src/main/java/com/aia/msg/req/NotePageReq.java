package com.aia.msg.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-16 17:20
 */
@Data
public class NotePageReq extends BasePageReq {

    @ApiModelProperty(value = "公告类型id")
    private Long noteTypeId;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    private Integer status;
}
