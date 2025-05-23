package com.sky.vo;


import com.sky.entiry.OrderDetail;
import com.sky.entiry.Orders;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/18 14:55
 * @description
 */
@Setter
@Getter
public class OrderVO extends Orders implements Serializable {

  //  每一个订单对应的运单状态
  private Integer  waybillStatus;

  //  骑手电话
  private  String  courierTelephone;

  //订单详情
  private List<OrderDetail> orderDetailList;


  //订单菜品信息
  private String orderDishes;
  // 顾客性别
  //性别 0 男性   1 女性
  private String sex;

  //商铺联系手机号
  private String shopTelephone;

  // 商铺名称
  private  String shopName;
}