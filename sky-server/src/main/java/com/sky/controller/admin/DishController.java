package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.DishService;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/15 12:27
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

  @Resource
  private DishService dishService;

  @DeleteMapping
  public Result<?> batchDelete(String ids) {
    dishService.batchDelete(ids);
    return Result.success();
  }


  @PostMapping("/status/{status}")
  public Result<?> updateStatus(@PathVariable Integer status, Long id) {
    log.info("修改菜品状态：{}", status);
    dishService.updateStatus(status, id);
    return Result.success();
  }


  @PutMapping
  public Result<?> update(@Valid @RequestBody DishDTO dishDTO) {
    log.info("修改菜品：{}", dishDTO);
    dishService.update(dishDTO);
    return Result.success();
  }


  @PostMapping
  public Result<?> save(@Valid @RequestBody DishDTO dishDTO) {
    log.info("新增菜品：{}", dishDTO);
    dishService.save(dishDTO);
    return Result.success();
  }


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
    List<Dish> list = dishService.list(categoryId);
    return Result.success(list);
  }


}