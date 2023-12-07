package com.aia.report.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chentao
 * @version 创建时间: 2023-11-15 10:30
 */
@Data
public class ReportMonthReq {

    @ApiModelProperty(value="开始日期",required = true)
    @NotBlank(message="开始日期不能为空")
    private String startMonth;

    @ApiModelProperty(value="结束日期",required = true)
    @NotBlank(message="结束日期不能为空")
    private String endMonth;
}
