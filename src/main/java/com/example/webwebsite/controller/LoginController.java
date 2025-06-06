package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.LoginInfo;
import com.example.webwebsite.pojo.LoginUser;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.pojo.User;
import com.example.webwebsite.service.UserService;
import com.example.webwebsite.utils.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author superG
 * @date 2025/5/29
 */



@RestController
@Slf4j
public class LoginController {


    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {
        System.out.println("loginUser = " + loginUser);

        log.info("用户名:{}", loginUser.getUsername());
        log.info("密码:{}", loginUser.getPassword());

        LoginInfo loginInfo = userService.getLoginInfo(loginUser);
        log.info("loginInfo = " + loginInfo);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }

}
