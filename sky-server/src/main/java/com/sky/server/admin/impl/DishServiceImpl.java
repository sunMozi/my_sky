package com.sky.server.admin.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.entiry.DishFlavor;
import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.server.admin.DishService;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import com.sky.vo.FlavorVO;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    return dishMapper.selectDishByCategoryId(categoryId);
  }

  @Override
  @Transactional
  public void save(DishDTO dishDTO) {
    LocalDateTime now = LocalDateTime.now();
    Dish dish = Dish.builder()
                    .createUser(BaseContext.getCurrentId())
                    .createTime(now)
                    .updateTime(now)
                    .updateUser(BaseContext.getCurrentId())
                    .build();
    BeanUtil.copyProperties(dishDTO, dish);
    dishMapper.insertDish(dish);

    List<DishFlavor> dishFlavors = dishDTO.getFlavors();
    if (dishFlavors == null || dishFlavors.isEmpty()) {
      return;
    }
    List<DishFlavor> list = dishFlavors.stream().map(dishFlavor ->

                                                     {
                                                       dishFlavor.setDishId(dish.getId());
                                                       DishFlavor build = DishFlavor.builder()
                                                                                    .dishId(dish.getId())
                                                                                    .build();
                                                       BeanUtil.copyProperties(dishFlavor, build);
                                                       return build;
                                                     }

    ).toList();

    dishFlavorMapper.insertDishFlavorBatch(list);
  }


  @Override
  @Transactional
  public void update(DishDTO dishDTO) {

    Dish dish = dishMapper.selectDishById(dishDTO.getId());
    if (dish == null) {
      throw new BaseException(ResponseCodeEnum.DATA_INVALID, "菜品不存在");
    }
    Dish dishByName = dishMapper.selectDishByName(dishDTO.getName());

    if (dishByName != null && !dishByName.getId().equals(dish.getId())) {
      throw new BaseException(ResponseCodeEnum.CONFLICT, "菜品已经存在");
    }

    Dish build = new Dish();
    BeanUtil.copyProperties(dishDTO, build);
    build.setUpdateTime(LocalDateTime.now());
    build.setUpdateUser(BaseContext.getCurrentId());
    dishMapper.update(build);
    if (dishDTO.getFlavors() != null) {
      dishFlavorMapper.deleteDishFlavorByDishId(dishDTO.getId());
      dishDTO.getFlavors().forEach(dishFlavor -> dishFlavor.setDishId(dishDTO.getId()));
      dishFlavorMapper.insertDishFlavorBatch(dishDTO.getFlavors());
    }
  }

  @Override
  public void updateStatus(Integer status, Long id) {
    Dish dish = Dish.builder()
                    .id(id)
                    .status(status)
                    .updateTime(LocalDateTime.now())
                    .updateUser(BaseContext.getCurrentId())
                    .build();
    dishMapper.update(dish);
  }

  @Override
  @Transactional
  public void batchDelete(String ids) {
    String[] split = ids.split(",");
    dishMapper.batchDeleteDishById(split);
    for (String id : split) {
      dishFlavorMapper.deleteDishFlavorByDishId(Long.valueOf(id));
    }
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