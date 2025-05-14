package com.sky.exception;

import java.text.MessageFormat;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {
    private final ResponseCodeEnum responseCode;  // 直接持有枚举
    private final Object[] args;                 // 消息动态参数

    public BaseException(ResponseCodeEnum responseCode, Object... args) {
        super(formatMessage(responseCode, args));
        this.responseCode = responseCode;
        this.args = args;
    }

    // 格式化消息（支持动态参数）
    private static String formatMessage(ResponseCodeEnum code, Object... args) {
        return args.length > 0 ?
            MessageFormat.format(code.getMsg(), args) :
            code.getMsg();
    }

    // Getter（直接暴露枚举）
    public ResponseCodeEnum getResponseCode() {
        return responseCode;
    }

    public Object[] getArgs() {
        return args;
    }
}