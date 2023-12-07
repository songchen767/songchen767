package com.aia.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-16 17:34
 */
@Data
public class NoteEditReq {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "公告内容")
    @NotBlank(message="公告内容不能为空")
    private String content;

    @ApiModelProperty(value = "公告类型ID")
    @NotNull(message="公告类型id不能为空")
    private Long noteTypeId;

    @ApiModelProperty(value="状态 0:停用;1:启用")
    @NotNull(message="状态不能为空")
    private Integer status;
}
