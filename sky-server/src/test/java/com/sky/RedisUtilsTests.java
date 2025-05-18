package com.sky;

import com.sky.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilsTests {

  @Resource
  private RedisUtils<String> redisUtils;


}