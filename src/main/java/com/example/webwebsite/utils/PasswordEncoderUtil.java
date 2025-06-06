package com.example.webwebsite.utils;

/**
 * @author superG
 * @date 2025/5/29
 */
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoderUtil {

    /**
     * 加密密码（使用 BCrypt）
     *
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword) {
        // 使用更安全的 salt 生成方式，默认会自动生成
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码是否匹配
     *
     * @param rawPassword 原始密码
     * @param hashedPassword 数据库中存储的加密密码
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}

