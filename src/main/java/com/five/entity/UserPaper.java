package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * (UserPaper)表实体类
 *
 * @author fly
 * @since 2023-05-08 14:13:59
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPaper{
    //id
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户id
    private Long userId;
    //试卷id
    private Long paperId;
    //开始时间
    private LocalDateTime startTime;
    //提交时间
    private LocalDateTime submitTime;
    //得分
    private Double score;
}

