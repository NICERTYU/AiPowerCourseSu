package com.example.webwebsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author superG
 * @date 2025/5/29
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    private Integer id;
    private String username;
    private String password;
    private String token;
}
