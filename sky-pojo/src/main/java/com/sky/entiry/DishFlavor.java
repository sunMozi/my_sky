package com.sky.entiry;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author moZiA
 * @date 2025/5/15 12:46
 * @description
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishFlavor {

  private Long id;
  private Long dishId;
  private String name;
  private String value;

}