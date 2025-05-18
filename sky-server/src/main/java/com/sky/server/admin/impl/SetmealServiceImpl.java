package com.sky.server.admin.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealDishDTO;
import com.sky.entiry.Setmeal;
import com.sky.entiry.SetmealDish;
import com.sky.exception.BaseException;
import com.sky.exception.ResponseCodeEnum;
import com.sky.mapper.admin.SetmealDishMapper;
import com.sky.mapper.admin.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.server.admin.SetmealService;
import com.sky.vo.SetmealVO;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author moZiA
 * @date 2025/5/18 10:37
 * @description
 */
@Service
public class SetmealServiceImpl implements SetmealService {

  @Resource
  private SetmealMapper setmealMapper;

  @Resource
  private SetmealDishMapper setmealDishMapper;

  @Override
  public PageResult<SetmealVO> page(SetmealDTO setmealDTO) {
    PageHelper.startPage(setmealDTO.getPage(), setmealDTO.getPageSize());
    List<SetmealVO> setmealList = setmealMapper.selectSetmealList(setmealDTO);

    Page<SetmealVO> page = (Page<SetmealVO>) setmealList;
    return new PageResult<>(page.getTotal(), page.getResult());
  }

  @Override
  @Transactional
  public void save(SetmealDishDTO setmealDTO) {
    Setmeal setmeal = new Setmeal();
    BeanUtil.copyProperties(setmealDTO, setmeal);
    setmeal.setCreateUser(BaseContext.getCurrentId());
    setmeal.setUpdateUser(BaseContext.getCurrentId());
    setmeal.setCreateTime(LocalDateTime.now());
    setmeal.setUpdateTime(LocalDateTime.now());
    setmeal.setStatus(StatusConstant.ENABLE);
    setmealMapper.insertSetmeal(setmeal);

    List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
    setmealDishes.forEach(setmealDish -> {
      setmealDish.setSetmealId(setmeal.getId());
    });
    setmealDishMapper.insertSetmealDishBatch(setmealDishes);

  }

  @Override
  public SetmealVO getById(Long id) {
    Setmeal setmeal = setmealMapper.selectSetmealById(id);
    SetmealVO setmealVO = new SetmealVO();
    BeanUtil.copyProperties(setmeal, setmealVO);
    List<SetmealDish> setmealDishs = setmealDishMapper.selectSetmealDishesListBySetmealId(id);
    setmealVO.setSetmealDishes(setmealDishs);
    return setmealVO;
  }

  @Override
  @Transactional
  public void update(SetmealDishDTO setmealDishDTO) {
    Setmeal setmeal = setmealMapper.selectSetmealById(setmealDishDTO.getId());
    if (setmeal == null) {
      throw new BaseException(ResponseCodeEnum.DATA_INVALID, "套餐不存在");
    }
    String newName = setmealDishDTO.getName();
    if (!newName.equals(setmeal.getName())) {
      Setmeal exists = setmealMapper.existsByNameExcludeId(newName, setmeal.getId());
      if (exists != null) {
        throw new BaseException(ResponseCodeEnum.DATA_INVALID, "套餐名称已存在");
      }
    }
    Setmeal newSetmeal = new Setmeal();
    BeanUtil.copyProperties(setmealDishDTO, newSetmeal);
    newSetmeal.setUpdateTime(LocalDateTime.now());
    newSetmeal.setUpdateUser(BaseContext.getCurrentId());
    setmealMapper.updateSetmeal(newSetmeal);

    setmealDishMapper.deleteSetmealDishesBySetmealId(setmeal.getId());
    setmealDishDTO.getSetmealDishes().forEach(setmealDish -> {
      setmealDish.setSetmealId(setmeal.getId());
    });
    setmealDishMapper.insertSetmealDishBatch(setmealDishDTO.getSetmealDishes());

  }

  @Override
  public void updateStatus(Integer status, Long id) {
    Setmeal build = Setmeal.builder()
                           .id(id)
                           .status(status)
                           .updateTime(LocalDateTime.now())
                           .updateUser(BaseContext.getCurrentId())
                           .build();
    setmealMapper.updateSetmeal(build);
  }

  @Override
  public void batchDelete(String ids) {
    String[] split = ids.split(",");
    setmealMapper.batchDeleteSetmealById(split);
    for (String id : split) {
      setmealDishMapper.deleteSetmealDishesBySetmealId(Long.valueOf(id));
    }
  }
}