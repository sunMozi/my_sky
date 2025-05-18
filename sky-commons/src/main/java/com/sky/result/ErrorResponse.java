package com.sky.result;


import lombok.Getter;

/**
 * @author moZiA
 * @date 2025/5/14 21:47
 * @description
 */
@Getter
public class ErrorResponse {
  private final int code;
  private final String message;

  // 修改构造函数：第三个参数用 Object[] 接收
  public ErrorResponse(int code, String message, Object[] details) {
    this.code = code;
    this.message = message;
  }
}