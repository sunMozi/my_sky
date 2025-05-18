package com.sky.server.admin;

import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import jakarta.validation.Valid;
import java.util.List;

/**
 * @author MoZi
 * @createTime 2025/5/15 12:28
 */
public interface DishService {

  List<Dish> list(Long categoryId);

  PageResult<DishVO> page(DishDTO dishDTO);

  DishFlavorVO getById(Long id);

  void save(DishDTO dishDTO);

  void update(@Valid DishDTO dishDTO);

  void updateStatus(Integer status, Long id);

  void batchDelete(String ids);
}
