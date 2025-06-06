package com.example.webwebsite.interceptor;


import com.example.webwebsite.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author superG
 * @date 2025/5/29
 */


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

       String token = request.getHeader("token");

       if(token == null ||  token.isEmpty()) {
           log.info("token为空");
           response.setStatus(401);
           return false;
       }

       try {
           log.info("token:{}********", token);
           Map<String, Object> claims = JwtUtils.parseToken(token);
           log.info("claims:{}********", claims);
           Integer userId = (Integer) claims.get("id");
           String username = (String) claims.get("username");

           // 将用户信息存入 request，Controller 中可直接获取
           request.setAttribute("currentUserId", userId);
           request.setAttribute("currentUsername", username);

       } catch (Exception e) {
           log.info("token解析失败");
           response.setStatus(401);
           return false;
       }



        log.info("token解析成功");
       return true;
    }




}
