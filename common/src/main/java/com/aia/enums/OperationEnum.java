package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-11-13 22:14
 */
public enum OperationEnum {

    /**
     * 冻结代理操作说明    RELATION:冻结自己以及代理下的会员
     */
    RELATION("RELATION",2),

    /**
     * own:只冻结自己
     */
    OWN("OWN",1),
    ;

    private String label;

    private Integer value;

    OperationEnum(String label,Integer value){
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
