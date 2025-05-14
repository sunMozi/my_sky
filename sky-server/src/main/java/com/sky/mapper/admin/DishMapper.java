package com.sky.mapper.admin;

import com.sky.dto.DishDTO;
import com.sky.entiry.Dish;
import com.sky.mapper.admin.sql.DishMapperProvider;
import com.sky.vo.DishFlavorVO;
import com.sky.vo.DishVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author MoZi
 * @createTime 2025/5/15 12:32
 */
@Mapper
public interface DishMapper {


  @SelectProvider(type = DishMapperProvider.class, method = "selectDishList")
  List<DishVO> selectDishList(@Param("dishDTO") DishDTO dishDTO);

}
