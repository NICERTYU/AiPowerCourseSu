package com.example.webwebsite.pojo;

/**
 * @author superG
 * @date 2025/5/29
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;              // 主键ID
    private String username;         // 用户名
    private String password;         // 密码（加密存储）
    private String nickname;         // 昵称
    private String avatar;           // 头像URL
    private String email;            // 邮箱
    private String phone;            // 手机号
    private String role;             // 角色（user/admin等）
    private Integer status;          // 状态（1正常 0禁用）
    private LocalDateTime createdAt; // 注册时间
    private LocalDateTime updatedAt; // 更新时间

}
