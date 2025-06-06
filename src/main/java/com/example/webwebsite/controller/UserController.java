package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.pojo.User;
import com.example.webwebsite.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author superG
 * @date 2025/5/29
 */


@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public Result registerUser(@RequestBody User user) {
        log.info("用户注册：{}", user);

        userService.registerUser(user);



        return Result.success("用户注册成功");

    }


    @PostMapping("/test")
    public Result hello() {
        return Result.success("hello");
    }

}
