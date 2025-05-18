package com.sky.entiry;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/18 14:03
 * @description
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {


  private Long id;

  //名称
  private String name;

  //订单id
  private Long orderId;

  //菜品id
  private Long dishId;

  //套餐id
  private Long setmealId;

  //口味
  private String dishFlavor;

  //数量
  private Integer number;

  //金额
  private BigDecimal amount;

  //图片
  private String image;
}