package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户-班级关联表，主要是针对于老师多个班的情况(UserClass)表实体类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserClass {
    //关联表主键
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户表主键
    private Long userId;
    //班级表主键
    private Long clazzId;
    //注册时间
    private LocalDateTime signTime;
    //注销时间
    private LocalDateTime deleteTime;

}

