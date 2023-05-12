package com.five.service.questions.model;

/**
 * @description 生成问题的一些参数限制
 */
public class QuestionParameterLimit {

    private Integer questionNum;

    private Integer minResultLimit;

    private Integer maxResultLimit = Integer.MAX_VALUE;

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getMinResultLimit() {
        return minResultLimit;
    }

    public void setMinResultLimit(Integer minResultLimit) {
        this.minResultLimit = minResultLimit;
    }

    public Integer getMaxResultLimit() {
        return maxResultLimit;
    }

    public void setMaxResultLimit(Integer maxResultLimit) {
        this.maxResultLimit = maxResultLimit;
    }
}
