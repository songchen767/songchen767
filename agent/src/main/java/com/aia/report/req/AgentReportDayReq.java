package com.aia.report.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-17 11:42
 */
@Data
public class AgentReportDayReq extends BasePageReq {

    @ApiModelProperty(value = "开始日期")
    private String startDate;

    @ApiModelProperty(value="结束日期")
    private String endDate;

}
