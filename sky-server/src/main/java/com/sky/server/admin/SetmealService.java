package com.sky.server.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealDishDTO;
import com.sky.entiry.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;
import jakarta.validation.Valid;

/**
 * @author MoZi
 * @createTime 2025/5/18 10:37
 */
public interface SetmealService {

  PageResult<SetmealVO> page(SetmealDTO setmealDTO);

  void save(@Valid SetmealDishDTO setmealDishDTO);

  SetmealVO getById(Long id);

  void update(SetmealDishDTO setmealDishDTO);

  void updateStatus(Integer status, Long id);

  void batchDelete(String ids);
}
