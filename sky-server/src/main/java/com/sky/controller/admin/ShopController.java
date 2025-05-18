package com.sky.controller.admin;


import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {


  @PutMapping("/{status}")
  public Result<String> startOrStop(@PathVariable Integer status) {
    log.info("修改店铺营业状态：{}", status == 1 ? "营业中" : "打烊中");
    return null;
  }

}