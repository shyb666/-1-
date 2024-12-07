package com.bite.book.config;

import com.bite.book.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public Result handlerException(Exception e){
        log.error("发生异常, e: {}", e);
        return Result.fail("内部错误");
    }

    @ExceptionHandler
    public Result handlerException(NullPointerException e){
        log.error("发生异常, e:", e);
        return Result.fail("发生空指针异常");
    }

    @ExceptionHandler
    public Result handlerException(ArithmeticException e){
        log.error("发生异常, e:", e);
        return Result.fail("发生算术异常");
    }
}
