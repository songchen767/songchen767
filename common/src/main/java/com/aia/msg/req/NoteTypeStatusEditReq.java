package com.aia.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 18:30
 */
@Data
public class NoteTypeStatusEditReq {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    @NotNull(message="状态不能为空")
    private Integer status;

}
