package com.sky.server.user.impl;


import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.server.user.CategoryService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/5/23 14:23
 * @description
 */
@Service("userCategoryService")
public class CategoryServiceImpl implements CategoryService {

  @Resource
  private CategoryMapper categoryMapper;


  @Override
  public List<Category> list(Integer type) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setType(type);
    return categoryMapper.selectCategoryList(categoryDTO);
  }
}