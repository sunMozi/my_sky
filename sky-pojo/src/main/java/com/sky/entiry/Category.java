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
 * @date 2025/5/15 13:06
 * @description
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

  private Long id;
  private Integer type;
  private String name;
  private Integer sort;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Long createUser;
  private Long updateUser;

}