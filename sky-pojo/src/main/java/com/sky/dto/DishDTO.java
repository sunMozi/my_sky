package com.sky.dto;


import com.sky.entiry.DishFlavor;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/15 12:40
 * @description
 */
@Setter
@Getter
public class DishDTO extends PageQuery {

  private Long categoryId;
  private String description;
  private List<DishFlavor> flavors;
  private Long id;
  private String image;
  private Integer makeTime;
  private String name;
  private Double price;
  private Integer status;

}