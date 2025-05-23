package com.sky.mapper;


import com.sky.entiry.User;
import com.sky.mapper.sql.UserMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author moZiA
 * @date 2025/5/19 20:18
 * @description
 */
@Mapper
public interface UserMapper {

  @Select("select * from user where openid = #{openid}")
  User selectByOpenId(String openId);

  @InsertProvider(type = UserMapperProvider.class, method = "insertUser")
  void insertUser(@Param("user") User user);
}