package com.sky.mapper.sql;


import com.sky.entiry.DishFlavor;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/15 20:44
 * @description
 */
public class DishFlavorMapperProvider {

  public String insertDishFlavorBatch(final Map<String, Object> params) {
    List<DishFlavor> list = (List<DishFlavor>) params.get("dishFlavor");
    return new SQL() {{
      INSERT_INTO("dish_flavor");
      INTO_COLUMNS("dish_id", "name", "value");
      for (int i = 0; i < list.size(); i++) {
        INTO_VALUES(
            "#{dishFlavor[" + i + "].dishId}",
            "#{dishFlavor[" + i + "].name}",
            "#{dishFlavor[" + i + "].value}"
        );
        if (i < list.size() - 1) {
          ADD_ROW();
        }
      }


    }}.toString();
  }


  public String selectDishFlavorByDishId(Long dishId) {
    return new SQL() {{
      SELECT("*");
      FROM("dish_flavor");
      WHERE("dish_id = #{dishId}");
    }}.toString();
  }

}