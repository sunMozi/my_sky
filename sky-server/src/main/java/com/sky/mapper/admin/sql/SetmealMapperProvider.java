package com.sky.mapper.admin.sql;


import com.sky.dto.SetmealDTO;
import com.sky.entiry.Setmeal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/18 10:44
 * @description
 */
public class SetmealMapperProvider {

  public String batchDeleteSetmealById(final Map<String, Object> params) {
    final String[] ids = (String[]) params.get("ids");
    return new SQL() {
      {
        DELETE_FROM("setmeal");

        String placeholder = IntStream.range(0, ids.length)
                                      .mapToObj(i -> "#{ids[" + i + "]}")
                                      .collect(Collectors.joining(","));

        WHERE("id IN (" + placeholder + ")");
      }
    }.toString();
  }

  public String updateSetmeal(final Map<String, Object> params) {
    final Setmeal setmeal = (Setmeal) params.get("setmeal");
    return new SQL() {
      {
        UPDATE("setmeal");
        buildSetClause(this, setmeal);
        WHERE("id = #{setmeal.id}");
      }
    }.toString();
  }

  private void buildSetClause(SQL sql, Setmeal setmeal) {
    if (setmeal.getCategoryId() != null) {
      sql.SET("category_id = #{setmeal.categoryId}");
    }
    if (setmeal.getName() != null) {
      sql.SET("name = #{setmeal.name}");
    }
    if (setmeal.getPrice() != null) {
      sql.SET("price = #{setmeal.price}");
    }
    if (setmeal.getStatus() != null) {
      sql.SET("status = #{setmeal.status}");
    }
    if (setmeal.getDescription() != null) {
      sql.SET("description = #{setmeal.description}");
    }
    if (setmeal.getImage() != null) {
      sql.SET("image = #{setmeal.image}");
    }
    sql.SET("update_time = #{setmeal.updateTime}");
    sql.SET("update_user = #{setmeal.updateUser}");
  }

  public String selectSetmealList(final Map<String, Object> params) {
    final SetmealDTO setmealDTO = (SetmealDTO) params.get("setmealDTO");
    return new SQL() {{
      SELECT("setmeal.*", "category.name as categoryName");
      FROM("setmeal");
      LEFT_OUTER_JOIN("category on setmeal.category_id = category.id");
      buildWhereClause(this, setmealDTO);
    }}.toString();
  }

  public String insertSetmeal(final Map<String, Object> params) {
    final Setmeal setmeal = (Setmeal) params.get("setmeal");
    return new SQL() {{
      INSERT_INTO("setmeal");
      buildValuesClause(this, setmeal);
    }}.toString();
  }


  private void buildValuesClause(SQL sql, Setmeal setmeal) {
    sql.VALUES("name", "#{setmeal.name}");
    sql.VALUES("category_id", "#{setmeal.categoryId}");
    sql.VALUES("price", "#{setmeal.price}");
    sql.VALUES("status", "#{setmeal.status}");
    if (setmeal.getDescription() != null) {
      sql.VALUES("description", "#{setmeal.description}");
    }
    sql.VALUES("image", "#{setmeal.image}");
    sql.VALUES("make_time", "#{setmeal.makeTime}");
  }

  private void buildWhereClause(SQL sql, SetmealDTO setmealDTO) {
    if (setmealDTO != null) {
      if (setmealDTO.getCategoryId() != null) {
        sql.WHERE("setmeal.category_id = #{setmealDTO.categoryId}");
      }
      if (setmealDTO.getName() != null) {
        sql.WHERE("setmeal.name LIKE CONCAT('%', #{setmealDTO.name}, '%')");
      }
      if (setmealDTO.getStatus() != null) {
        sql.WHERE("setmeal.status = #{setmealDTO.status}");
      }
    }
  }

}