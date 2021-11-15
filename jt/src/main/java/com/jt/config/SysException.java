package com.jt.config;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class SysException {

    @ExceptionHandler({RuntimeException.class, IOException.class})
    public SysResult failed(Throwable throwable) {
        throwable.printStackTrace();
        return SysResult.failed();
    }
}
