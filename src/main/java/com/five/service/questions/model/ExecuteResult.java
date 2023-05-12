package com.five.service.questions.model;

/**
 * @description 限制链执行结果
 */
public class ExecuteResult {

    /**
     * 题目是否合法
     */
    private boolean legal;

    /**
     * 题目
     */
    private Question question;

    /**
     * 题目数量达到最大值
     */
    private boolean isMaxNum;

    public ExecuteResult() {
    }

    public ExecuteResult(boolean isLimit, Question question) {
        this.legal = isLimit;
        this.question = question;
    }

    public ExecuteResult(boolean isLimit, Question question,boolean isMaxNum) {
        this.legal = isLimit;
        this.question = question;
        this.isMaxNum = isMaxNum;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isMaxNum() {
        return isMaxNum;
    }

    public void setMaxNum(boolean maxNum) {
        isMaxNum = maxNum;
    }
}
