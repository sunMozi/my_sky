package com.sky.mapper.admin;


import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import com.sky.mapper.admin.sql.categoryMapperProvider;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author moZiA
 * @date 2025/5/15 13:04
 * @description
 */
@Mapper
public interface categoryMapper {

  @SelectProvider(type = categoryMapperProvider.class, method = "selectCategoryList")
  List<Category> selectCategoryList(@Param("categoryDTO") CategoryDTO categoryDTO);

  @UpdateProvider(type = categoryMapperProvider.class, method = "updateStatus")
  void updateStatus(@Param("category") Category category);

  @UpdateProvider(type = categoryMapperProvider.class, method = "updateCategory")
  void updateCategory(@Param("category") Category category);

  @InsertProvider(type = categoryMapperProvider.class, method = "insertCategory")
  void insertCategory(@Param("category") Category category);

  @DeleteProvider(type = categoryMapperProvider.class, method = "deleteCategory")
  void deleteCategory(Long id);
}