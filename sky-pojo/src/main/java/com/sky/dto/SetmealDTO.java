package com.sky.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/18 10:34
 * @description
 */
@Setter
@Getter
public class SetmealDTO extends PageQuery {

  private String name;
  private Long categoryId;
  private Integer status;


}