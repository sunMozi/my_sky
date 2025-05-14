package com.sky.server.admin.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQuery;
import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.mapper.admin.EmployeeMapper;
import com.sky.entiry.Employee;
import com.sky.result.PageResult;
import com.sky.server.admin.EmployeeService;
import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
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
  public Employee login(EmployeeLoginDTO employeeLoginQuery) {
    String username = employeeLoginQuery.getUsername();
    String password = employeeLoginQuery.getPassword();

    Employee employee = employeeMapper.selectByUsername(username);
    System.out.println(employee);

    if (employee == null) {
      throw new BaseException(ResponseCodeEnum.PARAM_ERROR);
    }
    password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

    System.out.println(password);

    if (!password.equals(employee.getPassword())) {
      throw new BaseException(ResponseCodeEnum.PASSWORD_ERROR,"密码错误");
    }
    if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
      throw new BaseException(ResponseCodeEnum.ROLE_REQUIRED, "账号已被禁用");
    }
    return employee;
  }

  @Override
  public PageResult<Employee> page(EmployeePageQuery employeePageQuery) {
    employeePageQuery.checkPageParam();

    PageHelper.startPage(employeePageQuery.getPage(), employeePageQuery.getPageSize());
    List<Employee> employees = employeeMapper.selectEmployeeList(employeePageQuery);

    PageInfo<Employee> clazzPageInfo = new PageInfo<>(employees);
    return new PageResult<>(clazzPageInfo.getTotal(), clazzPageInfo.getList());
  }

  @Override
  public void save(EmployeeDTO employeeDTO) {

    Employee dbEmployee = employeeMapper.selectByUsername(employeeDTO.getUsername());
    if (dbEmployee != null) {
      throw new BaseException(ResponseCodeEnum.CONFLICT,
                              "用户" + employeeDTO.getName() + "已经存在");
    }

    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDTO, employee);
    employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
    employee.setStatus(1);
    employee.setCreateTime(LocalDateTime.now());
    employee.setUpdateTime(LocalDateTime.now());
    employee.setCreateUser(BaseContext.getCurrentId());
    employee.setUpdateUser(BaseContext.getCurrentId());

    employeeMapper.insertEmployee(employee);
  }

  @Override
  public void update(Employee employee) {

    employee.setUpdateUser(BaseContext.getCurrentId());
    employee.setUpdateTime(LocalDateTime.now());
    System.out.println(employee);
    employeeMapper.updateEmployee(employee);
  }

  @Override
  public Employee getById(Long id) {
    return employeeMapper.selectEmployeeById(id);
  }

  @Override
  public void updateStatus(Integer status, Long id) {
    Employee employee = Employee.builder()
                                .id(id)
                                .status(status)
                                .updateTime(LocalDateTime.now())
                                .updateUser(BaseContext.getCurrentId())
                                .build();
    employeeMapper.updateEmployee(employee);

  }
}