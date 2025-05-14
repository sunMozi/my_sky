package com.sky.mapper.admin.sql;


import com.sky.dto.CategoryDTO;
import com.sky.entiry.Category;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/15 13:21
 * @description
 */
public class categoryMapperProvider {

  public String deleteCategory(Long id) {
    return new SQL() {{
      DELETE_FROM("category");
      WHERE("id = #{id}");
    }}.toString();
  }

  public String insertCategory(final Map<String, Object> params) {
    final Category category = (Category) params.get("category");
    return new SQL() {{
      INSERT_INTO("category");
      buildInsertClause(this, category);
    }}.toString();
  }


  public String updateCategory(final Map<String, Object> params) {
    final Category category = (Category) params.get("category");
    return new SQL() {{
      UPDATE("category");
      buildSetClause(this, category);
    }}.toString();
  }


  public String updateStatus(final Map<String, Object> params) {
    final Category category = (Category) params.get("category");
    return new SQL() {{
      UPDATE("category");
      buildSetClause(this, category);
    }}.toString();
  }

  public String selectCategoryList(final Map<String, Object> params) {
    final CategoryDTO query = (CategoryDTO) params.get("categoryDTO");
    return new SQL() {{
      SELECT("*");
      FROM("category");
      buildWhereClause(this, query);
      buildOrderByClause(this);
    }}.toString();
  }


  private void buildSetClause(SQL sql, Category category) {
    if (category.getName() != null) {
      sql.SET("name = #{category.name}");
    }
    if (category.getSort() != null) {
      sql.SET("sort = #{category.sort}");
    }
    if (category.getStatus() != null) {
      sql.SET("status = #{category.status}");
    }
    if (category.getType() != null) {
      sql.SET("type = #{category.type}");
    }
    sql.SET("update_time = now()");
    sql.SET("update_user = #{category.updateUser}");
    // 最终条件
    sql.WHERE("id = #{category.id}");
  }

  private void buildOrderByClause(SQL sql) {
    sql.ORDER_BY("sort");
  }

  private void buildWhereClause(SQL sql, CategoryDTO query) {
    if (query.getName() != null) {
      sql.WHERE("name LIKE CONCAT('%', #{categoryDTO.name}, '%')");
    }
    if (query.getType() != null) {
      sql.WHERE("type = #{categoryDTO.type}");
    }
  }

  private void buildInsertClause(SQL sql, Category category) {
    sql.VALUES("type", "#{category.type}");
    sql.VALUES("name", "#{category.name}");
    sql.VALUES("sort", "#{category.sort}");
    sql.VALUES("status", "#{category.status}");
    sql.VALUES("create_time", "now()");
    sql.VALUES("update_time", "now()");
    sql.VALUES("create_user", "#{category.createUser}");
    sql.VALUES("update_user", "#{category.updateUser}");
  }

}