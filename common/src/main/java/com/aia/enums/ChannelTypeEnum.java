package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 17:25
 */
public enum ChannelTypeEnum {

    /**
     * 说明
     * CARD:银行卡
     */
    CARD("CARD", 0),

    /**
     * ALI_PAY:  支付宝
     */
    ALI_PAY("ALI_PAY", 1),

    /**
     * tengxun_pay :微信
     */
    TENGXUN_PAY("TENGXUN_PAY", 2),

    /**
     * 虚拟币
     */
    VM_PAY("VM_PAY", 3),
    ;

    private String label;

    private Integer value;

    ChannelTypeEnum(String label, Integer value) {
        this.value = value;
        this.label = label;
    }

    public String getLable() {
        return this.label;
    }

    public Integer getValue() {
        return this.value;
    }
}
