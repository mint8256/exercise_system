package com.five.service.questions.chain;


import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Question;
import com.five.service.questions.model.QuestionParameterLimit;

/**
 * @description 抽象执行链，用于检查Question和paper 的一系列要求
 */
public abstract class AbstractExecute {

    protected ArithmeticPaper arithmeticPaper;

    protected AbstractExecute nextExecute;

    protected QuestionParameterLimit questionParameterLimit;

    public void setQuestionParameterLimit(QuestionParameterLimit questionParameterLimit) {
        this.questionParameterLimit = questionParameterLimit;
    }

    public void setArithmeticPaper(ArithmeticPaper arithmeticPaper) {
        this.arithmeticPaper = arithmeticPaper;
    }

    public void setNextExecute(AbstractExecute nextExecute) {
        this.nextExecute = nextExecute;
    }


    public abstract ExecuteResult execute(Question question);
}
