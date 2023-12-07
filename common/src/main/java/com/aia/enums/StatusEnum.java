package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-11-14 17:07
 */
public enum StatusEnum {

    /**
     * 启用/停用说明    ON:启用
     */
    ON("ON",1),

    /**
     * OFF  停用
     */
    OFF("OFF",0),
    ;

    private String label;

    private Integer value;

    StatusEnum(String label,Integer value){
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
