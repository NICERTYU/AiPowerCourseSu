package com.example.webwebsite.exception;


import com.example.webwebsite.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author superG
 * @date 2025/5/28
 */


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {





    @ExceptionHandler
    public Result handleException(Exception e)
    {
        log.error("服务器发生异常: {}", e.getMessage());
        e.printStackTrace();
        return Result.error("服务器发生异常");
    }


    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库发生重复键异常: {}", e.getMessage());
        e.printStackTrace();
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("数据库发生重复键异常");
    }
}
