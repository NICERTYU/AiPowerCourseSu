package com.example.webwebsite;

import com.example.webwebsite.utils.JwtUtils;
import com.example.webwebsite.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WebWebsiteApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(PasswordEncoderUtil.encode("kl"));
    }


    @Test
    void test() {


        try {
            Map<String, Object> map = JwtUtils.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NiwidXNlcm5hbWUiOiJrbCIsImV4cCI6MTc0ODYxNDE1Nn0.F1MqcVU9qoPJH8VBQHQd06rIrRO2EFvWb8dty2d3F9M");

            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
