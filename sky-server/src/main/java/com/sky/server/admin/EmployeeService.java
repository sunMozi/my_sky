package com.sky.server.admin;


import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQuery;
import com.sky.entiry.Employee;
import com.sky.result.PageResult;

/**
 * @author moZiA
 * @date 2025/5/13 20:42
 * @description
 */
public interface EmployeeService {

  Employee login(EmployeeLoginDTO employeeLoginQuery);

  PageResult<Employee> page(EmployeePageQuery employeePageQuery);

  void save(EmployeeDTO employeeDTO);

  void update(Employee employee);

  Employee getById(Long id);

  void updateStatus(Integer status, Long id);
}