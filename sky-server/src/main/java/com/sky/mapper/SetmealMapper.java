package com.sky.mapper;

import com.sky.dto.SetmealDTO;
import com.sky.entiry.Setmeal;
import com.sky.mapper.sql.SetmealMapperProvider;
import com.sky.vo.SetmealVO;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author MoZi
 * @createTime 2025/5/18 10:37
 */
@Mapper
public interface SetmealMapper {

  @DeleteProvider(type = SetmealMapperProvider.class, method = "batchDeleteSetmealById")
  void batchDeleteSetmealById(@Param("ids") String[] ids);

  @SelectProvider(type = SetmealMapperProvider.class, method = "selectSetmealList")
  List<SetmealVO> selectSetmealList(@Param("setmealDTO") SetmealDTO setmealDTO);

  @InsertProvider(type = SetmealMapperProvider.class, method = "insertSetmeal")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertSetmeal(@Param("setmeal") Setmeal setmeal);

  @Select("select * from setmeal where id = #{id}")
  Setmeal selectSetmealById(Long id);

  @Select("select * from setmeal_dish where name = #{name}")
  Setmeal selectSetmealByName(String name);

  @UpdateProvider(type = SetmealMapperProvider.class, method = "updateSetmeal")
  void updateSetmeal(@Param("setmeal") Setmeal setmeal);

  @Select("select * from setmeal where name = #{newName} and id != #{id}")
  Setmeal existsByNameExcludeId(String newName, Long id);
}
