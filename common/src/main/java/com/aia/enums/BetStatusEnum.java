package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-12-04 14:36
 */
public enum BetStatusEnum {

    /**
     * UNSTART  未开奖
     */
    UNSTART("UNSTART",0),


    /**
     * WINNING  中奖
     */
    WINNING("WINNING",1),

    /**
     * UNWINNING 未中奖
     */
    UNWINNING("UNWINNING",2),

    /**
     * CANCEL 取消
     */
    CANCEL("CANCEL",3),

    ;



    private String label;

    private Integer value;

    BetStatusEnum(String label,Integer value){
        this.value = value;
        this.label = label;
    }

    public String getLable(){
        return this.label;
    }

    public Integer getValue(){
        return this.value;
    }
}
