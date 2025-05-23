package com.sky.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;


  private String username;

  private String name;

  private String password;

  private String phone;

  private String sex;

  private String idNumber;

  private Integer status;


  private LocalDateTime createTime;

  //@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime updateTime;

  private Long createUser;

  private Long updateUser;

}
