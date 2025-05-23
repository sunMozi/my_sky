package com.sky.controller.user;


import com.sky.entiry.Category;
import com.sky.result.Result;
import com.sky.server.user.CategoryService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/23 14:20
 * @description
 */
@RequestMapping("/user/category/")
@RestController("userCategoryController")
public class CategoryController {

  @Resource
  private CategoryService categoryService;

  @GetMapping("/list")
  private Result<List<Category>> list(Integer type) {
    return Result.success(categoryService.list(type));
  }


}