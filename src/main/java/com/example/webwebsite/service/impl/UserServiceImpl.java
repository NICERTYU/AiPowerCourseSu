package com.example.webwebsite.service.impl;

import com.example.webwebsite.mapper.UserMapper;
import com.example.webwebsite.pojo.LoginInfo;
import com.example.webwebsite.pojo.LoginUser;
import com.example.webwebsite.pojo.User;
import com.example.webwebsite.service.UserService;
import com.example.webwebsite.utils.JwtUtils;
import com.example.webwebsite.utils.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author superG
 * @date 2025/5/29
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void registerUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedAt(LocalDateTime.now());
        //密码加密

        user.setPassword(PasswordEncoderUtil.encode(user.getPassword()));

        userMapper.registerUser(user);

    }

    @Override
    public User getUserByUsername(String username) {
          return  userMapper.getUserByUsername(username);
    }

    @Override
    public LoginInfo getLoginInfo(LoginUser loginUser) {

        User  user = userMapper.getUserByUsernameAndPassword(loginUser.getUsername());

        if (user != null && PasswordEncoderUtil.matches(loginUser.getPassword(), user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String jwt= JwtUtils.generateToken(claims);
            log.info("token:{}********",jwt);


            return new LoginInfo(user.getId(), user.getUsername(),user.getAvatar(), jwt);
        }

        return null;
    }

    @Override
    public void updateAvatar(Integer userId, String url) {

        userMapper.updateAvatar(userId,url);

    }
}
