package com.sky.server.admin.impl;


import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.admin.EmployeeMapper;
import com.sky.pojo.Employee;
import com.sky.server.admin.EmployeeService;
import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author moZiA
 * @date 2025/5/13 20:43
 * @description
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Resource
  private EmployeeMapper employeeMapper;

  @Override
  public Employee login(EmployeeLoginDTO employeeLoginDTO) {
    String username = employeeLoginDTO.getUsername();
    String password = employeeLoginDTO.getPassword();



    Employee employee = employeeMapper.selectByUsername(username);
    System.out.println(employee);

    if (employee == null) {
      throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
    }
    password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

    System.out.println(password);

    if (!password.equals(employee.getPassword())) {
      throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
    }
    if (employee.getStatus() == StatusConstant.DISABLE) {
      throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
    }
    return employee;
  }
}