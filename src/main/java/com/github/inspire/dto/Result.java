package com.github.inspire.dto;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.code = 0;
        return result;
    }
}
