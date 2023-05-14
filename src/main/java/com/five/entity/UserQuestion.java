package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (UserQuestion)表实体类
 *
 * @author fly
 * @since 2023-05-09 15:38:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserQuestion {
    @TableId(type = IdType.AUTO)
    private Long id;
    //对用用户做过的试卷对应
    private Long userPaperId;
    //试卷id
    private Long paperId;
    //题目id
    private Long questionId;
    //用户答案
    private String userAnswer;
    //用户得分
    private Double userScore;
    //是否正确（0：错了，1：正确了）
    private Integer status;
}

