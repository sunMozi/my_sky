package com.sky.entiry;


import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/18 11:04
 * @description
 */
@Getter
@Setter
public class SetmealDish {

  private Long id;

  private Long setmealId;

  private Long dishId;

  private String name;

  private BigDecimal price;

  private Integer copies;
}