package com.sky.mapper;


import com.sky.entiry.SetmealDish;
import com.sky.mapper.sql.SetmealDishMapperProvider;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author moZiA
 * @date 2025/5/18 11:42
 * @description
 */
@Mapper
public interface SetmealDishMapper {

  @InsertProvider(type = SetmealDishMapperProvider.class, method = "insertSetmealDishBatch")
  void insertSetmealDishBatch(@Param("setmealDishes") List<SetmealDish> setmealDishes);

  @Select("select * from setmeal_dish where setmeal_id = #{id}")
  List<SetmealDish> selectSetmealDishesListBySetmealId(Long id);

  @Delete("delete from setmeal_dish where setmeal_id = #{id}")
  void deleteSetmealDishesBySetmealId(Long id);
}