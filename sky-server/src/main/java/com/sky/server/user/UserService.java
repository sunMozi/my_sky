package com.sky.server.user;


import com.sky.dto.UserLoginDTO;
import com.sky.vo.UserLoginVO;

/**
 * @author moZiA
 * @date 2025/5/19 20:17
 * @description
 */
public interface UserService {

  UserLoginVO login(UserLoginDTO dto);
}