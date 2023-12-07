package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-11-20 1:43
 */
public enum GamerTypeEnums {

    REAL_PERSON("REAL_PERSON",1),

    PHYSICAL("PHYSICAL",2),

    TIGER("TIGER",3),

    CHESS_CARDS("CHESS_CARDS",4),

    TICKET("TICKET",5),

    ;




    private String label;

    private Integer value;

    GamerTypeEnums(String label,Integer value){
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
