package com.sky.handler;

import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


  @ExceptionHandler(BaseException.class)
  public Result<String> handleBaseException(BaseException ex) {
    ex.printStackTrace();
    log.error("捕获到业务异常: {}", ex.getMessage(), ex);
    return Result.error(ex.getLocalizedMessage());
  }


}


