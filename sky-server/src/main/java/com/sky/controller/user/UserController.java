package com.sky.controller.user;


import com.sky.dto.UserLoginDTO;
import com.sky.result.Result;
import com.sky.server.user.UserService;
import com.sky.vo.UserLoginVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/5/19 20:16
 * @description
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

  @Resource
  private UserService userService;

  @PostMapping("/login")
  public Result<UserLoginVO> login(@RequestBody UserLoginDTO dto) {
    return Result.success(userService.login(dto));
  }


}