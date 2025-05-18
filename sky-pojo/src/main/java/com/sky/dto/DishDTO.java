package com.sky.dto;


import com.sky.entiry.DishFlavor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author moZiA
 * @date 2025/5/15 12:40
 * @description
 */
@Setter
@Getter
@ToString
public class DishDTO extends PageQuery {

  @NotNull(message = "不能为空")
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