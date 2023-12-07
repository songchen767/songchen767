package com.aia.msg.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-16 17:36
 */

@Data
public class NoteStatusEditReq {

    @ApiModelProperty(value="公告id",required = true)
    @NotNull(message="id不能为空")
    private Long id;

    @ApiModelProperty(value="状态 0:停用;1:启用",required = true)
    @NotNull(message="状态不能为空")
    private Integer status;

    @ApiModelProperty(value="发布人",required = true)
    private String publisher;

    @ApiModelProperty(value="撤销人",required = true)
    private String canceler;




}
