package com.sky.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 * @date 2025/5/19 20:22
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "sky.wechat")
public class WeChatProperties {

  private String appid;
  private String secret;

}