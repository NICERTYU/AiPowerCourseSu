package com.example.webwebsite.service;

import com.example.webwebsite.pojo.LoginInfo;
import com.example.webwebsite.pojo.LoginUser;
import com.example.webwebsite.pojo.User;

/**
 * @author superG
 * @date 2025/5/29
 */
public interface UserService {
    void registerUser(User user);

    User getUserByUsername(String username);

    LoginInfo getLoginInfo(LoginUser loginUser);

    void updateAvatar(Integer userId, String url);
}
