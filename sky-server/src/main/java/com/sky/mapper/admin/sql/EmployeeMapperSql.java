package com.sky.mapper.admin.sql;


/**
 * @author moZiA
 * @date 2025/5/13 20:43
 * @description
 */
public class EmployeeMapperSql {

  public String selectByUsername(String username) {
    return "SELECT * FROM employee WHERE username = #{username}";
  }

}