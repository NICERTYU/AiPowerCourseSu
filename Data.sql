CREATE TABLE `user` (
                        `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `username` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
                        `password` VARCHAR(255) NOT NULL COMMENT '密码（加密存储）',
                        `nickname` VARCHAR(32) DEFAULT NULL COMMENT '昵称',
                        `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
                        `email` VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
                        `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
                        `role` VARCHAR(16) DEFAULT 'user' COMMENT '角色（user/admin等）',
                        `status` TINYINT DEFAULT 1 COMMENT '状态（1正常 0禁用）',
                        `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                        `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


delete from user;



CREATE TABLE course (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
                        title VARCHAR(100) NOT NULL COMMENT '课程标题',
                        cover_url VARCHAR(255) DEFAULT NULL COMMENT '课程封面图片',
                        description TEXT COMMENT '课程简介',
                        teacher_id BIGINT DEFAULT NULL COMMENT '讲师ID',
                        category_id BIGINT DEFAULT NULL COMMENT '分类ID',
                        price DECIMAL(10,2) DEFAULT 0.00 COMMENT '价格',
                        learners INT DEFAULT 0 COMMENT '学习人数',
                        status TINYINT DEFAULT 1 COMMENT '状态 0下架 1上架',
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='课程表';







CREATE TABLE lesson (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '分集ID',
                        course_id BIGINT NOT NULL COMMENT '所属课程ID',
                        title VARCHAR(100) NOT NULL COMMENT '分集标题',
                        video_url VARCHAR(255) NOT NULL COMMENT '视频播放地址',
                        duration INT DEFAULT NULL COMMENT '视频时长（秒）',
                        sort INT DEFAULT 0 COMMENT '排序号',
                        description TEXT COMMENT '分集简介',
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        INDEX idx_course_id (course_id)
) COMMENT='课程分集表';

CREATE TABLE course (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
                        title VARCHAR(100) NOT NULL COMMENT '课程标题',
                        cover_url VARCHAR(255) DEFAULT NULL COMMENT '课程封面图片',
                        description TEXT COMMENT '课程简介',
                        teacher_id BIGINT DEFAULT NULL COMMENT '讲师ID',
                        category_id BIGINT DEFAULT NULL COMMENT '分类ID',
                        price DECIMAL(10,2) DEFAULT 0.00 COMMENT '价格',
                        learners INT DEFAULT 0 COMMENT '学习人数',
                        status TINYINT DEFAULT 1 COMMENT '状态 0下架 1上架',
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='课程表';




CREATE TABLE course_view_log (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                                 user_id INT UNSIGNED NOT NULL COMMENT '学员ID（关联user表）',
                                 course_id BIGINT NOT NULL COMMENT '课程ID（关联course表）',
                                 lesson_id BIGINT DEFAULT NULL COMMENT '分集ID（关联lesson表，可为空）',
                                 start_time DATETIME NOT NULL COMMENT '观看开始时间',
                                 end_time DATETIME DEFAULT NULL COMMENT '观看结束时间',
                                 duration_seconds INT DEFAULT 0 COMMENT '观看时长（秒）',
                                 device VARCHAR(50) DEFAULT NULL COMMENT '观看设备',
                                 region VARCHAR(100) DEFAULT NULL COMMENT '观看地区',
                                 created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                                 INDEX idx_user_id (user_id),
                                 INDEX idx_course_id (course_id),
                                 INDEX idx_lesson_id (lesson_id),
                                 FOREIGN KEY (user_id) REFERENCES user(id),
                                 FOREIGN KEY (course_id) REFERENCES course(id),
                                 FOREIGN KEY (lesson_id) REFERENCES lesson(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程观看日志表';



