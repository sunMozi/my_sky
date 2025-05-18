package com.sky.entiry;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author moZiA
 * @date 2025/5/18 10:27
 * @description
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Setmeal {

  private Long id;
  private Long categoryId;
  private String name;
  private String price;
  private Integer status;
  private String description;
  private String image;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Long createUser;
  private Long updateUser;
  private Integer makeTime;


}