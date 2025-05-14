package com.sky.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author moZiA
 * @date 2025/5/13 20:46
 * @description
 */
@Getter
@Setter
@ToString
public class EmployeeLoginDTO {

  private String password;
  private String username;

}