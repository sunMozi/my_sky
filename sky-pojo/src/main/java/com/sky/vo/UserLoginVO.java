package com.sky.vo;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/19 20:11
 * @description
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {

  private Long id;
  private String openid; // 小程序登录需要的openid
  private String token;//  token
  private String shopName; //  商家名称
  private String shopAddress; //  商家位置
  private String shopTelephone; //  商家电话
  private BigDecimal deliveryFee; //  配送费
  private Long shopId;//  商铺id
}