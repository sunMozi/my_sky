package com.sky.mapper.admin;

import com.sky.entiry.DishFlavor;
import com.sky.mapper.admin.sql.DishFlavorMapperProvider;
import com.sky.vo.FlavorVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author MoZi
 * @createTime 2025/5/15 20:44
 */
@Mapper
public interface DishFlavorMapper {

  @SelectProvider(type = DishFlavorMapperProvider.class, method = "selectDishFlavorByDishId")
  List<FlavorVO> selectDishFlavorByDishId(@Param("dishId") Long dishId);

}
