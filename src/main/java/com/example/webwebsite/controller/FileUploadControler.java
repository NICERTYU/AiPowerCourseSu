package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.UserService;
import com.example.webwebsite.utils.AliyunOSSOperator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author superG
 * @date 2025/5/29
 */


@RestController
@Slf4j
public class FileUploadControler {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    private UserService userService;


    @PostMapping("/upload")
    public Result upload(MultipartFile file, HttpServletRequest request) throws Exception {
        String username = (String) request.getAttribute("currentUsername");
        Integer userId = (Integer) request.getAttribute("currentUserId");

        log.info("用户 {} 正在上传文件", username);
        log.info("文件上传，参数：{}",file.getOriginalFilename());

        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());

        log.info("文件上传成功，返回结果：{}",url);
        userService.updateAvatar(userId, url);

        return Result.success(url);
    }
}
