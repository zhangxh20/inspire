package com.github.inspire.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SchedulerType {

    HALF_HOUR(1, "半个小时"),
    ONE_HOUR(2, "1个小时"),
    FOUR_HOUR(3, "4个小时"),
    ONE_DAY(4, "1天");

    private int code;

    private String des;

}
