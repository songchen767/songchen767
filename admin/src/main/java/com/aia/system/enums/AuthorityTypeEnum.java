package com.aia.system.enums;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @author chentao
 * @version 创建时间: 2023-11-10 11:37
 */
public enum AuthorityTypeEnum {

    menu("menu",1),

    button("button",2),

    function("function",3);

    private String label;

    private int value;

    AuthorityTypeEnum( String label,int value) {
        this.value = value;
        this.label = label;

    }

    public String getLabel() {
        return this.label;
    }

    public int getValue() {
        return this.value;
    }
}
