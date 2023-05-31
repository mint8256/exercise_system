package com.five.vo;

import com.five.entity.UserQuestion;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/22 13:29
 */
@Data
public class UserPaperDetail {

    //id
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
    //
    private LocalDateTime endTime;

    //持续时间(单位：s)
    private Long duration;
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
    //用户做题列表
    private List<UserQuestion> userQuestionList;

}
