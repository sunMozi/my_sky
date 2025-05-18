package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealDishDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.SetmealService;
import com.sky.vo.SetmealVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/18 10:36
 * @description
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

  @Resource
  private SetmealService setmealService;

  @DeleteMapping
  public Result<?> batchDelete(@RequestParam("ids") String ids) {
    setmealService.batchDelete(ids);
    return Result.success();
  }

  @PostMapping("/status/{status}")
  public Result<String> updateStatus(@PathVariable Integer status, @RequestParam Long id) {
    setmealService.updateStatus(status, id);
    return Result.success();
  }


  @PutMapping
  public Result<String> update(@RequestBody SetmealDishDTO setmealDishDTO) {
    setmealService.update(setmealDishDTO);
    return Result.success();
  }

  @GetMapping("{id}")
  public Result<SetmealVO> getById(@PathVariable Long id) {
    return Result.success(setmealService.getById(id));
  }

  @PostMapping
  public Result<?> save(@Valid @RequestBody SetmealDishDTO setmealDishDTO) {
    log.info("新增套餐：{}", setmealDishDTO);
    setmealService.save(setmealDishDTO);
    return Result.success();
  }


  @GetMapping("page")
  public Result<PageResult<SetmealVO>> page(SetmealDTO setmealDTO) {
    return Result.success(setmealService.page(setmealDTO));
  }


}