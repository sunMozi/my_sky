package com.sky.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author moZiA
 * @date 2025/5/23 11:14
 * @description
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI swaggerOpenApi() {
    return new OpenAPI().info(new Info().title("API文档")
                                        .version("1.0.0")
                                        .description("API文档描述")
                                        .contact(new Contact().name("开发者")
                                                              .email("developer@example.com"))
                                        .license(new License().name("Apache 2.0")
                                                              .url(
                                                                  "https://www.apache.org/licenses/LICENSE-2.0.html")))

                        .externalDocs(new ExternalDocumentation().description("设计文档")
                                                                 .url(
                                                                     "https://juejin.cn/user/254742430749736/posts"));
  }


}