package com.five.service.questions.chain;


import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Arithmetic;

/**
 * @description 数量检查并执行下一步
 */
public class NumCheckExecute extends AbstractExecute{


    @Override
    public ExecuteResult execute(Arithmetic arithmetic) {
        if (this.arithmeticPaper.getQuestionNums() >= questionParameterLimit.getQuestionNum()){
            return new ExecuteResult(false, arithmetic,true);
        }
        if (nextExecute != null){
            return nextExecute.execute(arithmetic);
        }
        return new ExecuteResult(true, arithmetic);
    }

}
