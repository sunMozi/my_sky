package com.sky.mapper.admin.sql;


import com.sky.dto.DishDTO;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/15 12:53
 * @description
 */
public class DishMapperProvider {

  public String selectDishList(final Map<String, Object> params) {
    final DishDTO query = (DishDTO) params.get("dishDTO");
    return new SQL() {{
      SELECT("dish.*", "category.name AS category_name");
      FROM("dish");
      LEFT_OUTER_JOIN("category ON dish.category_id = category.id");
      buildWhereClause(this, query);
    }}.toString();
  }

  private void buildWhereClause(SQL sql, DishDTO query) {
    if (query.getCategoryId() != null) {
      sql.WHERE("category_id = #{dishDTO.categoryId}");
    }
    if (query.getName() != null) {
      sql.WHERE("name LIKE CONCAT('%', #{dishDTO.name}, '%')");
    }
    if (query.getStatus() != null) {
      sql.WHERE("dish.status = #{dishDTO.status}");
    }
    if (query.getId() != null) {
      sql.WHERE("id = #{dishDTO.id}");
    }
  }

}