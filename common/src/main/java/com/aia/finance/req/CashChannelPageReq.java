package com.aia.finance.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 18:20
 */

@Data
public class CashChannelPageReq extends BasePageReq {

    @ApiModelProperty(value="名称")
    private String name;

    @ApiModelProperty(value="渠道类型")
    private String type;

    @ApiModelProperty(value="状态")
    private String status;

}
