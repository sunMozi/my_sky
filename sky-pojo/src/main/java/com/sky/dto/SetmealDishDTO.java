package com.sky.dto;


import com.sky.entiry.SetmealDish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/18 11:24
 * @description
 */
@Setter
@Getter
public class SetmealDishDTO {

  private Long id;

  @NotNull(message = "套餐分类不能为空")
  private Long categoryId;

  @NotBlank(message = "套餐名称不能为空")
  @Size(max = 50, message = "套餐名称长度不能超过50字符")
  private String name;

  @NotNull(message = "套餐价格不能为空")
  @Positive(message = "套餐价格必须为正数")
  private BigDecimal price;

  @NotNull(message = "套餐状态不能为空")
  private Integer status;

  @Size(max = 200, message = "描述长度不能超过200字符")
  private String description;

  @NotBlank(message = "套餐图片不能为空")
  private String image;

  @NotNull(message = "制作时间不能为空")
  @Positive(message = "制作时间必须为正数")
  private Integer makeTime;

  @NotNull(message = "套餐包含的菜品不能为空")
  @Size(min = 1, message = "套餐至少需要包含一个菜品")
  private List<SetmealDish> setmealDishes = new ArrayList<>();


}