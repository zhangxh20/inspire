package com.github.inspire.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataType {

    DATABASE(1, "数据库"),
    HTTP(2, "HTTP"),
    Groovy(3, "Groovy");

    private int code;

    private String desc;
}
