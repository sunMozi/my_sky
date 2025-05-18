package com.sky.mapper.admin.sql;


import com.sky.entiry.SetmealDish;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/18 11:44
 * @description
 */
public class SetmealDishMapperProvider {

  public String insertSetmealDishBatch(final Map<String, Object> params) {
    final List<SetmealDish> list = (List<SetmealDish>) params.get("setmealDishes");
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("SetmealDish list cannot be null or empty");
    }

    final String[] columns = {"setmeal_id", "dish_id", "name", "price", "copies"};

    return new SQL() {{
      INSERT_INTO("setmeal_dish");
      INTO_COLUMNS(columns);
      for (int i = 0; i < list.size(); i++) {
        INTO_VALUES("#{setmealDishes[" + i + "].setmealId}",
                    "#{setmealDishes[" + i + "].dishId}",
                    "#{setmealDishes[" + i + "].name}",
                    "#{setmealDishes[" + i + "].price}",
                    "#{setmealDishes[" + i + "].copies}");
        if (i < list.size() - 1) {
          ADD_ROW();
        }
      }

    }}.toString();
  }

}