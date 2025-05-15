package com.sky.server.admin.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.entiry.DishFlavor;
import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.mapper.admin.DishFlavorMapper;
import com.sky.mapper.admin.DishMapper;
import com.sky.result.PageResult;
import com.sky.server.admin.DishService;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import com.sky.vo.FlavorVO;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/5/15 12:28
 * @description
 */
@Service
public class DishServiceImpl implements DishService {

  @Resource
  private DishMapper dishMapper;

  @Resource
  private DishFlavorMapper dishFlavorMapper;


  @Override
  public List<Dish> list(Long categoryId) {
    return List.of();
  }

  @Override
  public PageResult<DishVO> page(DishDTO dishDTO) {

    PageHelper.startPage(dishDTO.getPage(), dishDTO.getPageSize());
    List<DishVO> dishes = dishMapper.selectDishList(dishDTO);
    Page<DishVO> page = (Page<DishVO>) dishes;
    PageResult<DishVO> result = new PageResult<>();
    result.setTotal(page.getTotal());
    result.setRecords(page.getResult());
    return result;
  }

  @Override
  public DishFlavorVO getById(Long id) {
    DishDTO dishDTO = new DishDTO();
    dishDTO.setId(id);
    List<DishVO> dishes = dishMapper.selectDishList(dishDTO);
    if (dishes.isEmpty()) {
      throw new BaseException(ResponseCodeEnum.DATA_INVALID, "菜品不存在");
    }
    if (dishes.size() > 1) {
      throw new BaseException(ResponseCodeEnum.DATA_INVALID, "菜品信息错误");
    }
    DishVO dishVO = dishes.get(0);
    List<FlavorVO> flavors = dishFlavorMapper.selectDishFlavorByDishId(dishVO.getId());
    DishFlavorVO dishFlavorVO = DishFlavorVO.builder().flavors(flavors).build();
    BeanUtil.copyProperties(dishVO, dishFlavorVO);
    return dishFlavorVO;
  }
}