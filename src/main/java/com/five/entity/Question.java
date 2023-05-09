package com.five.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (Question)表实体类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question{
    //问题id
    private Long questionId;
    //题目列表id
    private Long questionListId;
    //题目序号
    private Integer order;
    //题干
    private String stem;
    //题支
    private String questionBranch;
    //答案
    private String questionAnswer;
    //分数
    private Double score;
    //题目难度
    private String questionDifficulty;
}

