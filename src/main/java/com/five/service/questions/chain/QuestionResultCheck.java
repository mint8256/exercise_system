package com.five.service.questions.chain;


import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.ExecuteResult;

/**
 * @description 问题结果检查
 */
public class QuestionResultCheck extends AbstractExecute{


    @Override
    public ExecuteResult execute(Arithmetic arithmetic) {
        Integer answer = arithmetic.getAnswer();
        // 判断结果的区间。
        if (answer < questionParameterLimit.getMinResultLimit() || answer > questionParameterLimit.getMaxResultLimit()){
            return new ExecuteResult();
        }
        if (nextExecute != null){
            return nextExecute.execute(arithmetic);
        }
        return new ExecuteResult(true, arithmetic);
    }

}
