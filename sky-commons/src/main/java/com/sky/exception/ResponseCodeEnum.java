package com.sky.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author moZiA
 * @date 2025/5/14 21:41
 * @description
 */
@Getter
public enum ResponseCodeEnum {
  // 成功状态（通常不用于异常）
  CODE_200(200, "请求成功", HttpStatus.OK),

  // ---------- 客户端错误 (4xx) ----------
  PARAM_ERROR(400, "请求参数错误: {0}", HttpStatus.BAD_REQUEST),
  UNAUTHORIZED(401, "未授权访问: {0}", HttpStatus.UNAUTHORIZED),
  FORBIDDEN(403, "权限不足: {0}", HttpStatus.FORBIDDEN),
  NOT_FOUND(404, "{0} 不存在", HttpStatus.NOT_FOUND),
  CONFLICT(409, "数据冲突: {0}", HttpStatus.CONFLICT),
  TOO_MANY_REQUESTS(429, "请求过于频繁", HttpStatus.TOO_MANY_REQUESTS),

  // 登录/注册相关
  ACCOUNT_NOT_FOUND(404, "账户不存在: {0}", HttpStatus.NOT_FOUND),
  PASSWORD_ERROR(401, "密码错误", HttpStatus.UNAUTHORIZED),
  ACCOUNT_LOCKED(403, "账户已锁定", HttpStatus.FORBIDDEN),
  CAPTCHA_ERROR(400, "验证码错误", HttpStatus.BAD_REQUEST),
  SOCIAL_AUTH_FAILED(401, "{0} 授权失败", HttpStatus.UNAUTHORIZED),

  // 权限相关
  ACCESS_DENIED(403, "没有权限访问该资源: {0}", HttpStatus.FORBIDDEN),
  ROLE_REQUIRED(403, "需要 {0} 角色才能访问", HttpStatus.FORBIDDEN),

  // 数据操作相关
  DATA_DUPLICATE(409, "{0} 已存在", HttpStatus.CONFLICT),
  DATA_EXPIRED(410, "{0} 已过期", HttpStatus.GONE),
  DATA_INVALID(400, "{0} 数据无效", HttpStatus.BAD_REQUEST),

  // 文件操作相关
  FILE_TOO_LARGE(413, "文件大小超过限制", HttpStatus.PAYLOAD_TOO_LARGE),
  FILE_TYPE_INVALID(415, "不支持的文件类型: {0}", HttpStatus.UNSUPPORTED_MEDIA_TYPE),

  // ---------- 服务端错误 (5xx) ----------
  INTERNAL_ERROR(500, "服务内部错误: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
  SERVICE_UNAVAILABLE(503, "服务暂不可用", HttpStatus.SERVICE_UNAVAILABLE),
  DB_CONNECTION_FAILED(500, "数据库连接失败", HttpStatus.INTERNAL_SERVER_ERROR),
  THIRD_PARTY_ERROR(502, "第三方服务异常: {0}", HttpStatus.BAD_GATEWAY);

  private final int code;
  private final String msg;
  private final HttpStatus httpStatus;

  ResponseCodeEnum(int code, String msg, HttpStatus httpStatus) {
    this.code = code;
    this.msg = msg;
    this.httpStatus = httpStatus;
  }
}