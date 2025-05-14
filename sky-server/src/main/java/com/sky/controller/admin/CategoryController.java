package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.categoryService;
import jakarta.annotation.Resource;
import java.util.List;
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
 * @date 2025/5/15 13:03
 * @description
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

  @Resource
  private categoryService categoryService;

  @DeleteMapping
  public Result<?> delete(Long id) {
    categoryService.delete(id);
    return Result.success();
  }

  @PostMapping
  public Result<?> save(@RequestBody CategoryDTO categoryDTO) {
    categoryService.save(categoryDTO);
    return Result.success();
  }

  @PutMapping
  private Result<?> update(@RequestBody CategoryDTO categoryDTO) {
    categoryService.update(categoryDTO);
    return Result.success();
  }


  @PostMapping("status/{status}")
  public Result<?> updateStatus(@PathVariable Integer status, Long id) {
    categoryService.updateStatus(status, id);
    return Result.success();
  }


  @GetMapping("/page")
  public Result<PageResult<Category>> page(CategoryDTO categoryDTO) {
    PageResult<Category> result = categoryService.page(categoryDTO);
    return Result.success(result);
  }


  @GetMapping("/list")
  public Result<List<Category>> list(Integer type) {
    return Result.success(categoryService.list(type));
  }

}