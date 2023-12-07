package com.aia.msg.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-12-05 11:12
 */
@Data
public class NoteListReq extends BasePageReq {

    @ApiModelProperty(value = "公告类型id", required = true)
    @NotNull(message = "公告类型id不能为空")
    private Long noteTypeId;
}
