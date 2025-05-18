package com.sky.mapper.admin;

import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.mapper.admin.sql.DishMapperProvider;
import com.sky.vo.DishVO;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author MoZi
 * @createTime 2025/5/15 12:32
 */
@Mapper
public interface DishMapper {


  @SelectProvider(type = DishMapperProvider.class, method = "selectDishList")
  List<DishVO> selectDishList(@Param("dishDTO") DishDTO dishDTO);

  @InsertProvider(type = DishMapperProvider.class, method = "insertDish")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertDish(@Param("dish") Dish dish);

  @Select("select * from dish where category_id = #{id}")
  List<Dish> selectDishByCategoryId(Long id);

  @Select("select * from dish where id = #{id}")
  Dish selectDishById(Long id);

  @UpdateProvider(type = DishMapperProvider.class, method = "update")
  void update(@Param("dish") Dish dish);

  @Select("select * from dish where name = #{name}")
  Dish selectDishByName(String name);

  @DeleteProvider(type = DishMapperProvider.class, method = "batchDeleteDishById")
  void batchDeleteDishById(@Param("ids") String[] ids);
}
