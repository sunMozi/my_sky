package com.sky.mapper.admin.sql;


import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/15 20:44
 * @description
 */
public class DishFlavorMapperProvider {

  public String selectDishFlavorByDishId(Long dishId) {
    return new SQL() {{
      SELECT("*");
      FROM("dish_flavor");
      WHERE("dish_id = #{dishId}");
    }}.toString();
  }

}