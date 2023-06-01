package com.five.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 *
 * @author fly
 * @since 2023/6/1 19:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestionDetail {
    private Long id;
    //对用用户做过的试卷对应
    private Long userPaperId;
    //试卷id
    private Long paperId;
    //题目id
    private Long questionId;
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
    //用户答案
    private String userAnswer;
    //用户得分
    private Double userScore;
    //题目分数
    private Double questionScore;
    //是否正确（0：错了，1：对了，2：未做）
    private Integer status;
}
