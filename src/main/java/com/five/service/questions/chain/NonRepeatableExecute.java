package com.five.service.questions.chain;


import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Question;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 不能存在重复题目的检查
 */
public class NonRepeatableExecute extends AbstractExecute{

    private final Map<String,Boolean> cacheNonRepeatable = new HashMap<>();



    @Override
    public ExecuteResult execute(Question question) {
        // 检查是否重复
        if (Boolean.FALSE.equals(cacheNonRepeatable.get(question.toString()))){
            return new ExecuteResult();
        }
        if (nextExecute != null){
            // 如果后续有执行链，那么则继续执行
            ExecuteResult nextExecutionResult = nextExecute.execute(question);
            // 如果结果合法也将题目添加到已有题目中。
            if (nextExecutionResult.isLegal()){
                cacheNonRepeatable.put(question.toString(),true);
            }
            return nextExecutionResult;
        }
        // 没有后续执行链则直接添加到已有题目中
        cacheNonRepeatable.put(question.toString(),true);
       return new ExecuteResult(true, question);
    }


}
