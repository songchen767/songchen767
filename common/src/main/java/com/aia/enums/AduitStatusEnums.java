package com.aia.enums;

/**
 * 审核状态
 * @author chentao
 * @version 创建时间: 2023-11-19 23:28
 */
public enum AduitStatusEnums {

    /**
     * 审核状态说明
     *
     * UNADUIT:未审核
     */
    UNADUIT("UNADUIT",0),

    /**
     * PASS  审核通过
     */
    PASS("PASS",1),


    /**
     * 驳回
     */
    REFUND("REFUND",2),


    /**
     * 取消
     */
    CANCEL("CANCEL",3),


    ;

    private String label;

    private Integer value;

    AduitStatusEnums(String label,Integer value){
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
