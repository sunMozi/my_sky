package com.sky.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author moZiA
 * @date 2025/5/14 12:56
 * @description
 */
@Getter
@Setter
@ToString
public class EmployeePageQuery extends PageQuery {
  private String name;
}