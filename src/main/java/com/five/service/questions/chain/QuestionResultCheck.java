package com.five.service.questions.chain;


import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Question;

/**
 * @description 问题结果检查
 */
public class QuestionResultCheck extends AbstractExecute{


    @Override
    public ExecuteResult execute(Question question) {
        Integer answer = question.getAnswer();
        // 判断结果的区间。
        if (answer < questionParameterLimit.getMinResultLimit() || answer > questionParameterLimit.getMaxResultLimit()){
            return new ExecuteResult();
        }
        if (nextExecute != null){
            return nextExecute.execute(question);
        }
        return new ExecuteResult(true,question);
    }

}
