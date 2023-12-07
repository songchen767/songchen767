package com.aia.personal.req;

import com.aia.base.req.BasePageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chentao
 * @version 创建时间: 2023-11-28 17:52
 */

@Data
public class BetRecordPageReq extends BasePageReq {

    @ApiModelProperty(value="游戏类型")
    private Integer gameType;

    @ApiModelProperty(value="投注开始时间")
    private Date startTime;

    @ApiModelProperty(value="投注结束时间")
    private Date endTime;

    @ApiModelProperty(value="状态 0：未开奖\n" +
            "1：中奖\n" +
            "2：未中奖\n" +
            "3：已取消")
    private Integer status;


}
