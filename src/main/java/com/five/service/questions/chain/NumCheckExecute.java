package com.five.service.questions.chain;


import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Question;

/**
 * @description 数量检查并执行下一步
 */
public class NumCheckExecute extends AbstractExecute{


    @Override
    public ExecuteResult execute(Question question) {
        if (this.arithmeticPaper.getQuestionNums() >= questionParameterLimit.getQuestionNum()){
            return new ExecuteResult(false,question,true);
        }
        if (nextExecute != null){
            return nextExecute.execute(question);
        }
        return new ExecuteResult(true,question);
    }

}
