package com.sky.dto;


import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/5/14 11:14
 * @description
 */
@Setter
@Getter
public class PageQuery {


  private Integer page;
  private Integer pageSize;


  private static final Integer DEFAULT_PAGE = 1;
  private static final Integer DEFAULT_PAGE_SIZE = 10;
  private static final Integer ONE = 1;


  public void checkPageParam() {
    if (Objects.isNull(page) || page < ONE) {
      page = DEFAULT_PAGE;
    }
    if (pageSize == null || pageSize < ONE) {
      pageSize = DEFAULT_PAGE_SIZE;
    }
  }


}