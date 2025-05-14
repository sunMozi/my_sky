package com.sky.handler;

import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.result.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
    ResponseCodeEnum codeEnum = ex.getResponseCode();

    ErrorResponse response = new ErrorResponse(codeEnum.getCode(), codeEnum.getMsg(), ex.getArgs());
    ex.printStackTrace();
    return ResponseEntity.status(codeEnum.getHttpStatus()) // 直接关联HTTP状态码
                         .body(response);
  }
}
