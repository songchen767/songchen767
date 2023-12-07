package com.aia.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 18:27
 */
@Data
public class NoteTypeAddReq {

    @ApiModelProperty(value="公告类型名称")
    @NotBlank(message = "类型名称不能为空")
    private String name;

    @ApiModelProperty(value = "状态 0:停用;1:启用")
    @NotNull(message="状态不能为空")
    private Integer status;
}
