package com.five.enums;

/**
 * @description 题目难度枚举
 * @Author cxk
 * @Date 2023/5/9 16:59
 */
public enum QuestionDifficultyEnum {


    A("简单",1),
    B("中等",2),
    C("困难",3),
    ;

    // 难度描述
    private final String des;
    // 得分
    private final Integer score;

    QuestionDifficultyEnum(String desc,Integer score){
        this.des = desc;
        this.score = score;
    }

    public String getDes() {
        return des;
    }

    public Integer getScore() {
        return score;
    }
}
