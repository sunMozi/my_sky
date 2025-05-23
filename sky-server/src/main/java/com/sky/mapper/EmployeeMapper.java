package com.sky.mapper;


import com.sky.dto.EmployeePageQuery;
import com.sky.mapper.sql.EmployeeMapperProvider;
import com.sky.entiry.Employee;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author moZiA
 * @date 2025/5/13 20:43
 * @description
 */
@Mapper
public interface EmployeeMapper {

  @SelectProvider(type = EmployeeMapperProvider.class, method = "selectByUsername")
  Employee selectByUsername(String username);


  @SelectProvider(type = EmployeeMapperProvider.class, method = "selectEmployeeList")
  List<Employee> selectEmployeeList(@Param("employeePageQuery") EmployeePageQuery employeePageQuery);


  @InsertProvider(type = EmployeeMapperProvider.class, method = "insertEmployee")
  void insertEmployee(@Param("employee") Employee employee);

  @Select("select * from employee where id = #{id}")
  Employee selectEmployeeById(Long id);

  @UpdateProvider(type = EmployeeMapperProvider.class, method = "updateEmployee")
  void updateEmployee(@Param("employee") Employee employee);
}