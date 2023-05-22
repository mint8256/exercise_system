package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户做的试卷记录(UserPaper)表实体类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPaper {
    //id
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户id
    private Long userId;
    //试卷id
    private Long paperId;
    //试卷名称
    private String paperName;
    //开始时间
    private LocalDateTime startTime;
    //提交时间
    private LocalDateTime submitTime;
    //用户做对的题目数量
    private Integer questionCorrect;
    //试卷题目总数
    private Integer questionCount;
    //用户得分
    private Double userScore;
    //试卷总分
    private Double paperScore;
    //试卷类型：（0：未开始，1：未完成，2：已做成，3：已批改）
    private Integer status;
}

