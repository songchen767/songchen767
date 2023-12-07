package com.aia.finance.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 17:34
 */
@Data
public class IncomeSettingAddReq {

    @ApiModelProperty(value="渠道ID")
    @NotNull(message = "渠道ID不能为空")
    private Long channelId;

    @ApiModelProperty(value="渠道类型")
    @NotNull(message = "渠道类型不能为空")
    private Integer type;

    @ApiModelProperty(value="银行卡号")
    private String bankCardNo;

    @ApiModelProperty(value="支付宝账号")
    private String alipay;

    @ApiModelProperty(value="微信账号")
    private String wechat;

    @ApiModelProperty(value="虚拟币地址")
    private String coin;

    @ApiModelProperty(value="户名")
    private String account;

    @ApiModelProperty(value="银行名称")
    private String bankName;

    @ApiModelProperty(value="状态")
    @NotNull(message = "状态不能为空")
    private Integer status;


}
