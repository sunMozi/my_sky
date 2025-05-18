package com.sky.server.admin.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import com.sky.entiry.Dish;
import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.mapper.admin.DishMapper;
import com.sky.mapper.admin.categoryMapper;
import com.sky.result.PageResult;
import com.sky.server.admin.categoryService;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/5/15 13:04
 * @description
 */
@Service
public class CategoryServiceImpl implements categoryService {

  @Resource
  private categoryMapper categoryMapper;

  @Resource
  private DishMapper dishMapper;


  @Override
  public List<Category> list(Integer type) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setType(type);
    return categoryMapper.selectCategoryList(categoryDTO);
  }

  @Override
  public PageResult<Category> page(CategoryDTO categoryDTO) {
    PageHelper.startPage(categoryDTO.getPage(), categoryDTO.getPageSize());
    List<Category> categories = categoryMapper.selectCategoryList(categoryDTO);
    Page<Category> page = (Page<Category>) categories;
    PageResult<Category> result = new PageResult<>();
    result.setTotal(page.getTotal());
    result.setRecords(page.getResult());
    return result;
  }

  @Override
  public void updateStatus(Integer status, Long id) {
    Category category = new Category();
    category.setStatus(status);
    category.setId(id);
    category.setUpdateTime(LocalDateTime.now());
    category.setUpdateUser(BaseContext.getCurrentId());
    categoryMapper.updateStatus(category);
  }

  @Override
  public void save(CategoryDTO categoryDTO) {
    Category category = new Category();
    BeanUtil.copyProperties(categoryDTO, category);
    category.setUpdateUser(BaseContext.getCurrentId());
    category.setStatus(StatusConstant.DISPATCH_OK);
    categoryMapper.insertCategory(category);
  }

  @Override
  public void update(CategoryDTO categoryDTO) {
    Category category = new Category();
    BeanUtil.copyProperties(categoryDTO, category);
    category.setUpdateTime(LocalDateTime.now());
    category.setUpdateUser(BaseContext.getCurrentId());
    categoryMapper.updateCategory(category);
  }

  @Override
  public void delete(Long id) {
    List<Dish> dishes = dishMapper.selectDishByCategoryId(id);
    if (!dishes.isEmpty()) {
      throw new BaseException(ResponseCodeEnum.CONFLICT, "该分类下有菜品，不能删除");
    }

    categoryMapper.deleteCategory(id);
  }
}