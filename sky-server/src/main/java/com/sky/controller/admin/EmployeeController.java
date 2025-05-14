package com.sky.controller.admin;


import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQuery;
import com.sky.entiry.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.server.admin.EmployeeService;
import com.sky.utils.JwtUtils;
import com.sky.vo.EmployeeLoginVO;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/13 20:44
 * @description
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

  @Resource
  private EmployeeService employeeService;

  @Resource
  private JwtProperties jwtProperties;

  @GetMapping("{id}")
  public Result<?> getById(@PathVariable Long id) {
    log.info("根据id查询员工信息：{}", id);
    return Result.success(employeeService.getById(id));
  }

  @PostMapping("status/{status}")
  public Result<?> updateStatus(@PathVariable Integer status, Long id) {
    log.info("员工状态修改：{}", status);
    employeeService.updateStatus(status, id);
    return Result.success();
  }


  @PutMapping
  public Result<?> update(@RequestBody Employee employee) {
    log.info("修改员工信息：{}", employee);
    employeeService.update(employee);
    return Result.success();
  }


  @PostMapping
  public Result<?> save(@RequestBody EmployeeDTO employeeDTO) {
    log.info("新增员工：{}", employeeDTO);
    employeeService.save(employeeDTO);
    return Result.success();
  }


  @GetMapping("page")
  public Result<PageResult<Employee>> page(EmployeePageQuery employeePageQueryDTO) {
    return Result.success(employeeService.page(employeePageQueryDTO));
  }


  @PostMapping("/login")
  public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
    log.info("员工登录：{}", employeeLoginDTO);
    Employee employee = employeeService.login(employeeLoginDTO);
    //登录成功后，生成jwt令牌
    Map<String, Object> claims = new HashMap<>();
    claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
    String token = JwtUtils.createJWT(jwtProperties.getAdminSecretKey(),
                                      jwtProperties.getAdminTtl(),
                                      claims);
    EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                                                     .id(employee.getId())
                                                     .userName(employee.getUsername())
                                                     .name(employee.getName())
                                                     .token(token)
                                                     .build();
    return Result.success(employeeLoginVO);
  }


  @PostMapping("/logout")
  public Result<String> logout() {
    return Result.success();
  }


}