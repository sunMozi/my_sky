package com.sky.mapper.admin.sql;


import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/15 12:53
 * @description
 */
public class DishMapperProvider {

  public String batchDeleteDishById(final Map<String, Object> params) {
    final String[] ids = (String[]) params.get("ids");
    return new SQL() {{
      DELETE_FROM("dish");

      String placeholder = IntStream.range(0, ids.length)
                                    .mapToObj(i -> "#{ids[" + i + "]}")
                                    .collect(Collectors.joining(","));


      WHERE("id IN (" + placeholder + ")");
    }}.toString();

  }


  public String update(final Map<String, Object> params) {
    final Dish dish = (Dish) params.get("dish");
    return new SQL() {{
      UPDATE("dish");
      buildSettClause(this, dish);
      WHERE("id = #{dish.id}");
    }}.toString();
  }

  public String insertDish(final Map<String, Object> params) {
    final Dish dish = (Dish) params.get("dish");
    return new SQL() {{
      INSERT_INTO("dish");
      buildValuesClause(this, dish);
    }}.toString();
  }


  public String selectDishList(final Map<String, Object> params) {
    final DishDTO query = (DishDTO) params.get("dishDTO");
    return new SQL() {{
      SELECT("dish.*", "category.name AS category_name");
      FROM("dish");
      LEFT_OUTER_JOIN("category ON dish.category_id = category.id");
      buildWhereClause(this, query);
      buildOrderByClause(this, "dish.create_time desc");
    }}.toString();
  }

  private void buildSettClause(SQL sql, Dish dish) {
    if (dish.getName() != null) {
      sql.SET("name = #{dish.name}");
    }
    if (dish.getCategoryId() != null) {
      sql.SET("category_id = #{dish.categoryId}");
    }
    if (dish.getPrice() != null) {
      sql.SET("price = #{dish.price}");
    }
    if (dish.getMakeTime() != null) {
      sql.SET("make_time = #{dish.makeTime}");
    }
    if (dish.getImage() != null) {
      sql.SET("image = #{dish.image}");
    }
    if (dish.getDescription() != null) {
      sql.SET("description = #{dish.description}");
    }
    if (dish.getStatus() != null) {
      sql.SET("status = #{dish.status}");
    }

  }

  private void buildOrderByClause(SQL sql, String... attribute) {
    sql.ORDER_BY(attribute);
  }

  private void buildValuesClause(SQL sql, Dish query) {
    if (query != null) {
      sql.VALUES("name", "#{dish.name}");
      sql.VALUES("category_id", "#{dish.categoryId}");
      sql.VALUES("price", "#{dish.price}");
      sql.VALUES("image", "#{dish.image}");
      if (query.getDescription() != null) {
        sql.VALUES("description", "#{dish.description}");
      }

      sql.VALUES("make_time", "#{dish.makeTime}");
      sql.VALUES("create_time", "#{dish.createTime}");
      sql.VALUES("update_time", "#{dish.updateTime}");
      sql.VALUES("create_user", "#{dish.createUser}");
      sql.VALUES("update_user", "#{dish.updateUser}");
    }
  }

  private void buildWhereClause(SQL sql, DishDTO query) {
    if (query.getCategoryId() != null) {
      sql.WHERE("category_id = #{dishDTO.categoryId}");
    }
    if (query.getName() != null) {
      sql.WHERE("dish.name LIKE CONCAT('%', #{dishDTO.name}, '%')");
    }
    if (query.getStatus() != null) {
      sql.WHERE("dish.status = #{dishDTO.status}");
    }
    if (query.getId() != null) {
      sql.WHERE("dish.id = #{dishDTO.id}");
    }
  }

}