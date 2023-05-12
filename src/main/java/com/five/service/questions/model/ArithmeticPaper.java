package com.five.service.questions.model;


import java.util.List;

/**
 * @description 数学试卷
 */
public class ArithmeticPaper {



    public void setArithmetics(List<Question> arithmetics) {
        this.arithmetics = arithmetics;
    }


    /**
     * 题目列表
     */
    private List<Question> arithmetics;


    public ArithmeticPaper(){}

    public List<Question> getArithmetics() {
        return arithmetics;
    }

    /**
     * 总的问题数量
     * @return 总题数
     */
    public Integer getQuestionNums(){
        return arithmetics.size();
    }

}
