package com.sky.config;


import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.interceptor.JwtTokenUserInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author moZiA
 * @date 2025/5/14 13:55
 * @description
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

  @Resource
  private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  @Resource
  private JwtTokenUserInterceptor jwtTokenUserInterceptor;

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    log.info("开始注册自定义拦截器...");
    //  配置后台服务端
    registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/employee/login");
    registry.addInterceptor(jwtTokenUserInterceptor)
            .addPathPatterns("/user/**")
            .excludePathPatterns("/user/user/login")
            .excludePathPatterns("/user/shop/status")
            .excludePathPatterns("/user/user/selectById")
            .excludePathPatterns("/user/user/save")
            .excludePathPatterns("/user/user/delete")
            .excludePathPatterns("/user/order/nativePay");
  }


}