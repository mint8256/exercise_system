package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (Arithmetic)表实体类
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
    @TableId(type = IdType.AUTO)
    private Long questionId;
    //题目列表id
    private Long questionListId;
    //题目序号
    @TableField("`order`")
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

