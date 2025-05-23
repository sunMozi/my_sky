package com.sky.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/19 20:11
 * @description
 */
@Setter
@Getter
public class UserLoginDTO {

  private String telephone;

  private String code;

  private String location; //  当前用户的经纬度   116.481488,39.990464
}