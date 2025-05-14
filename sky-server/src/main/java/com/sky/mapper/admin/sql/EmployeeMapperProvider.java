package com.sky.mapper.admin.sql;


import com.sky.dto.EmployeePageQuery;
import com.sky.entiry.Employee;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/13 20:43
 * @description
 */
public class EmployeeMapperProvider {


  public String updateEmployee(final Map<String, Object> params) {
    final Employee employee = (Employee) params.get("employee");
    return new SQL() {{
      UPDATE("employee");
      if (employee.getUsername() != null && !employee.getUsername().isEmpty()) {
        SET("username = #{employee.username}");
      }
      if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
        SET("password = #{employee.password}");
      }
      if (employee.getPhone() != null && !employee.getPhone().isEmpty()) {
        SET("phone = #{employee.phone}");
      }
      if (employee.getName() != null && !employee.getName().isEmpty()) {
        SET("name = #{employee.name}");
      }
      if (employee.getSex() != null && !employee.getSex().isEmpty()) {
        SET("sex = #{employee.sex}");
      }
      if (employee.getIdNumber() != null && !employee.getIdNumber().isEmpty()) {
        SET("id_number = #{employee.idNumber}");
      }
      if (employee.getStatus() != null) {
        SET("status = #{employee.status}");
      }
      SET("update_time = #{employee.updateTime}");
      SET("update_user = #{employee.updateUser}");
      WHERE("id = #{employee.id}");
    }}.toString();

  }


  public String selectByUsername(String username) {
    return "SELECT * FROM employee WHERE username = #{username}";
  }

  public String selectEmployeeList(final Map<String, Object> params) {
    final EmployeePageQuery query = (EmployeePageQuery) params.get("employeePageQuery");
    return new SQL() {{
      SELECT("*");
      FROM("employee");
      buildWhereClause(this, query);
    }}.toString();
  }

  public String insertEmployee(final Map<String, Object> params) {
    final Employee employee = (Employee) params.get("employee");
    return new SQL() {{
      INSERT_INTO("employee");
      VALUES("username", "#{employee.username}");
      VALUES("name", "#{employee.name}");
      VALUES("password", "#{employee.password}");
      VALUES("phone", "#{employee.phone}");
      VALUES("sex", "#{employee.sex}");
      VALUES("id_number", "#{employee.idNumber}");
      VALUES("status", "#{employee.status}");
      VALUES("create_time", "#{employee.createTime}");
      VALUES("update_time", "#{employee.updateTime}");
      VALUES("create_user", "#{employee.createUser}");
      VALUES("update_user", "#{employee.updateUser}");

    }}.toString();

  }


  // 查询 通用条件
  private void buildWhereClause(SQL sql, EmployeePageQuery query) {
    System.out.println(query);
    if (query.getName() != null) {
      sql.WHERE("name LIKE CONCAT('%', #{employeePageQuery.name}, '%')");
    }
  }

  // 排序 条件
  private void applyPaginationAndOrder(SQL sql, EmployeePageQuery query) {
  }

}