package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.DishService;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/15 12:27
 * @description
 */
@RestController
@RequestMapping("/admin/dish")
public class DishController {

  @Resource
  private DishService dishService;

  @GetMapping("{id}")
  public Result<DishFlavorVO> getById(@PathVariable Long id) {
    DishFlavorVO dishVO = dishService.getById(id);
    return Result.success(dishVO);
  }

  @GetMapping("page")
  public Result<PageResult<DishVO>> page(DishDTO dishDTO) {
    PageResult<DishVO> result = dishService.page(dishDTO);
    return Result.success(result);
  }

  @GetMapping("list")
  public Result<List<Dish>> list(Long categoryId) {
    return Result.success();
  }


}