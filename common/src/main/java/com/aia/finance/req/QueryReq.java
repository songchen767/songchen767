package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chentao
 * @version 创建时间: 2023-12-04 18:24
 */
@Data
public class QueryReq implements Serializable {

    @ApiModelProperty(value = "积分渠道id", required = true)
    @NotNull(message = "积分渠道id不能为空")
    private Long channeId;
}
