package com.tony.springboot.demo.advice;

import com.tony.springboot.demo.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数错误:", e);
        return R.error("参数错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数JSON解析错误:", e);
        return R.error("参数JSON解析错误");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error("发生异常", e);
        return R.error(e.getMessage());
    }
}
