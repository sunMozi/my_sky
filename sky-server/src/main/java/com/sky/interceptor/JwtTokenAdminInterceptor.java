package com.sky.interceptor;


import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
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
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

  @Resource
  private JwtProperties jwtProperties;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    String token = request.getHeader(jwtProperties.getAdminTokenName());
    try {
      log.info("jwt校验:{}", token);

      Map<String, Object> parseJWT = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(), token);

      Long l = Long.valueOf(parseJWT.get(JwtClaimsConstant.EMP_ID).toString());
      log.info("当前后台员工id：{}", l);
      BaseContext.setCurrentId(l);
      return true;
    } catch (Exception ex) {
      response.setStatus(401);
      return false;
    }
  }


}