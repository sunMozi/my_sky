package com.sky.mapper.sql;


import com.sky.entiry.User;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author moZiA
 * @date 2025/5/19 20:49
 * @description
 */
public class UserMapperProvider {

  public String insertUser(final Map<String, Object> params) {
    final User user = (User) params.get("user");
    return new SQL() {{
      INSERT_INTO("user");
      if (user.getOpenid() != null) {
        VALUES("openid", "#{user.openid}");
      }
      if (user.getName() != null){
        VALUES("name", "#{user.name}");
      }
      if (user.getPhone() != null){
        VALUES("phone", "#{user.phone}");
      }
      if (user.getSex() != null){
        VALUES("sex", "#{user.sex}");
      }
      if (user.getIdNumber() != null){
        VALUES("id_number", "#{user.idNumber}");
      }
      if (user.getAvatar() != null){
        VALUES("avatar", "#{user.avatar}");
      }
      if (user.getCreateTime() != null){
        VALUES("create_time", "#{user.createTime}");
      }
    }}.toString();
  }

}