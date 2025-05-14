package com.sky.server.admin;


import com.sky.dto.EmployeeLoginDTO;
import com.sky.pojo.Employee;
import com.sky.vo.EmployeeLoginVO;

/**
 * @author moZiA
 * @date 2025/5/13 20:42
 * @description
 */
public interface EmployeeService {

  Employee login(EmployeeLoginDTO employeeLoginDTO);
}