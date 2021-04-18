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

    public static Result<Void> fail(String message) {
        Result<Void> result = new Result<>();
        result.setMessage(message);
        result.code = 1;
        return result;
    }
}
