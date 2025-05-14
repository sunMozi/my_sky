package com.sky.mapper.admin;


import com.sky.mapper.admin.sql.EmployeeMapperSql;
import com.sky.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author moZiA
 * @date 2025/5/13 20:43
 * @description
 */
@Mapper
public interface EmployeeMapper {

  @SelectProvider(type = EmployeeMapperSql.class, method = "selectByUsername")
  Employee selectByUsername(String username);
}