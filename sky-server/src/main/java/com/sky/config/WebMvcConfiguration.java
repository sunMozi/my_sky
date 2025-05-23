package com.sky.config;


import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.interceptor.JwtTokenUserInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    // 后台拦截器
    registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/employee/login")
            .excludePathPatterns("/doc.html")              // 正确排除路径
            .excludePathPatterns("/webjars/**")
            .excludePathPatterns("/v3/api-docs/**")
            .excludePathPatterns("/swagger-ui/**");        // 新增 Swagger UI 排除

    // 用户端拦截器
    registry.addInterceptor(jwtTokenUserInterceptor)
            .addPathPatterns("/user/**")
            .excludePathPatterns(
                "/user/user/login",
                "/user/shop/status",
                "/user/user/selectById",
                "/user/user/save",
                "/user/user/delete",
                "/user/order/nativePay",
                "/doc.html",                // 正确排除路径
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**"            // 新增 Swagger UI 排除
            );
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 添加 SpringDoc 的 Swagger UI 资源路径
    registry.addResourceHandler("/swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/");

    // 保留 knife4j 的路径（如果使用 knife4j）
    registry.addResourceHandler("/doc.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }


}