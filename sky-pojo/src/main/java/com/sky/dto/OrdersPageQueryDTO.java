package com.sky.dto;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author moZiA
 * @date 2025/5/18 14:37
 * @description
 */
@Getter
@Setter
public class OrdersPageQueryDTO extends PageQuery {


  private String number;
  private String phone;
  private Integer status;
  private Integer payStatus;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime beginTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;

  private Long userId;
}