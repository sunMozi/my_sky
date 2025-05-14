package com.sky.vo;


import com.sky.entiry.DishFlavor;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moZiA
 * @date 2025/5/15 19:28
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavorVO {

  private Long id;
  private String name;
  private Long categoryId;
  private String categoryName;
  private Double price;
  private String image;
  private String description;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Long createUser;
  private Long updateUser;
  private Integer makeTime;
  private List<FlavorVO> flavors;

}