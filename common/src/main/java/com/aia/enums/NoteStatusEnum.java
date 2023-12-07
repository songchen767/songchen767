package com.aia.enums;

/**
 * @author chentao
 * @version 创建时间: 2023-11-16 17:25
 */
public enum NoteStatusEnum {

    /**
     * 发布说明    ON:启用
     */
    UNPUBLISHED("UNPUBLISHED",0),

    /**
     * 已经发布
     */
    PUBLISHED("PUBLISHED",1),

    /**
     * 撤销
     */
    CANCEL("CANCEL",2),
    ;

    private String label;

    private Integer value;

    NoteStatusEnum(String label,Integer value){
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
