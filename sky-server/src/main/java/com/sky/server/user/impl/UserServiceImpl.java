package com.sky.server.user.impl;


import com.alibaba.fastjson2.JSON;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entiry.User;
import com.sky.mapper.UserMapper;
import com.sky.properties.JwtProperties;
import com.sky.properties.WeChatProperties;
import com.sky.server.user.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.JwtUtils;
import com.sky.vo.UserLoginVO;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/5/19 20:17
 * @description
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

  @Resource
  private WeChatProperties weChatProperties;

  @Resource
  private JwtProperties jwtProperties;

  @Resource
  private UserMapper userMapper;

  @Override
  public UserLoginVO login(UserLoginDTO dto) {
    String openId = getOpenId(dto.getCode());
    User user = userMapper.selectByOpenId(openId);
    if (user == null) {
      User build = User.builder().openid(openId).createTime(LocalDateTime.now()).build();
      userMapper.insertUser(build);
    }

    Map<String, Object> claims = new HashMap<>();
    claims.put(JwtClaimsConstant.USER_ID, user != null ? user.getId() : null);
    String token = JwtUtils.createJWT(jwtProperties.getUserSecretKey(),
                                      jwtProperties.getUserTtl(),
                                      claims);

    return UserLoginVO.builder()
                      .openid(openId)
                      .token(token)
                      .shopName("")
                      .shopTelephone("")
                      .shopId(1L)
                      .shopAddress("")
                      .id(user != null ? user.getId() : null)
                      .deliveryFee(null)
                      .build();

  }


  private String getOpenId(String code) {
    Map<String, String> map = new HashMap<>();
    map.put("appid", weChatProperties.getAppid());
    map.put("secret", weChatProperties.getSecret());
    map.put("js_code", code);
    map.put("grant_type", "authorization_code");
    String jsonStr = HttpClientUtil.doGet(WX_LOGIN, map);
    log.info("微信返回的json字符串：{}", jsonStr);
    Map res = JSON.parseObject(jsonStr, Map.class);
    return res.get("openid").toString();
  }

}