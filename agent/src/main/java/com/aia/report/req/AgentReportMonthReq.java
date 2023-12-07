package com.aia.report.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-17 11:59
 */
@Data
public class AgentReportMonthReq extends BasePageReq {

    @ApiModelProperty(value = "开始月份")
    private String startMonth;

    @ApiModelProperty(value="结束月份")
    private String endMonth;
}
