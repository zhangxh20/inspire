package com.github.inspire.common.exception;

import com.github.inspire.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result<Void> handle(Exception e) {
        log.error("controller excepiton", e);
        return Result.fail(e.getMessage());
    }
}
