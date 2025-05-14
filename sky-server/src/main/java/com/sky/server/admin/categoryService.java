package com.sky.server.admin;


import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import com.sky.result.PageResult;
import java.util.List;

/**
 * @author moZiA
 * @date 2025/5/15 13:04
 * @description
 */
public interface categoryService {

  List<Category> list(Integer type);

  PageResult<Category> page(CategoryDTO categoryDTO);

  void updateStatus(Integer status, Long id);

  void save(CategoryDTO categoryDTO);

  void update(CategoryDTO categoryDTO);

  void delete(Long id);
}