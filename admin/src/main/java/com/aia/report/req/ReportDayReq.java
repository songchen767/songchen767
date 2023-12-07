package com.aia.report.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 10:20
 */
@Data
public class ReportDayReq {

    @ApiModelProperty(value="开始时间",required = true)
    @NotBlank(message="开始日期不能为空")
    private String startDate;

    @ApiModelProperty(value="结束日期",required = true)
    @NotBlank(message="结束日期不能为空")
    private String endDate;

}
