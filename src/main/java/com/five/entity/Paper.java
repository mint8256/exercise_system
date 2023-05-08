package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * (Paper)表实体类
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Paper {
    //试卷id
    @TableId(type = IdType.AUTO)
    private Long paperId;
    //开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
    //持续时间
    private LocalDateTime duration;
    //题目列表id（来源于哪个题目列表）
    private Long questionListId;
    //试卷编号
    private String paperIdentifier;
    //创建时间
    private LocalDateTime signTime;
}

