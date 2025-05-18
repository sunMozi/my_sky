package com.sky.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 * @date 2025/5/16 19:52
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
  private String endpoint;
  private String accessKeyId;
  private String bucketName;
  private String accessKeySecret;
}
