package com.sky.interceptor;


import com.sky.properties.JwtProperties;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author moZiA
 * @date 2025/5/14 13:56
 * @description
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

  @Resource
  private JwtProperties jwtProperties;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (!(handler instanceof HandlerMethod)) {
      //当前拦截到的不是动态方法，直接放行
      return true;
    }
    String token = request.getHeader(jwtProperties.getUserTokenName());

    try {
      log.info("jwt校验:{}", token);

      return true;
    } catch (Exception ex) {
      response.setStatus(401);
      return false;
    }
  }
}