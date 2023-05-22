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
    private Arithmetic arithmetic;

    /**
     * 题目数量达到最大值
     */
    private boolean isMaxNum;

    public ExecuteResult() {
    }

    public ExecuteResult(boolean isLimit, Arithmetic arithmetic) {
        this.legal = isLimit;
        this.arithmetic = arithmetic;
    }

    public ExecuteResult(boolean isLimit, Arithmetic arithmetic, boolean isMaxNum) {
        this.legal = isLimit;
        this.arithmetic = arithmetic;
        this.isMaxNum = isMaxNum;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public Arithmetic getQuestion() {
        return arithmetic;
    }

    public void setQuestion(Arithmetic arithmetic) {
        this.arithmetic = arithmetic;
    }

    public boolean isMaxNum() {
        return isMaxNum;
    }

    public void setMaxNum(boolean maxNum) {
        isMaxNum = maxNum;
    }
}
