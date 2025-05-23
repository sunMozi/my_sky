package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.utils.RedisUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/18 15:31
 * @description
 */
@RestController
@RequestMapping("/admin/shop/")
@Component("adminShopController")
@Slf4j
@Tag(name = "店铺接口")
public class ShopController {


  @Resource
  private RedisUtils<Integer> redisUtils;

  @PutMapping("/{status}")
  public Result<String> startOrStop(@PathVariable Integer status) {
    log.info("修改店铺营业状态：{}", status == 1 ? "营业中" : "打烊中");
    redisUtils.set("SHOP_STATUS", status);
    return Result.success();
  }

  @GetMapping("status")
  public Result<Integer> getStatus() {
    log.info("查询店铺营业状态");
    return Result.success(redisUtils.get("SHOP_STATUS"));
  }

}