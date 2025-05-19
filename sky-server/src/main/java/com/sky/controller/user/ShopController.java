package com.sky.controller.user;


import com.sky.result.Result;
import com.sky.utils.RedisUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/18 15:31
 * @description
 */
@RestController
@RequestMapping("/user/shop/")
@Component("userShopController")
@Slf4j
public class ShopController {


  @Resource
  private RedisUtils<Integer> redisUtils;

  @GetMapping("status")
  public Result<Integer> getStatus() {
    log.info("查询店铺营业状态");
    return Result.success(redisUtils.get("SHOP_STATUS"));
  }

}