package com.five.service.questions.question;



import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;

import java.util.List;

/**
 * @description
 */
public interface QuestionGenerator {

    /**
     * 生成一个问题
     * @param operateNums 操作数集合
     * @return 返回一个问题
     */
    Arithmetic generator(List<Integer> operateNums);

    /**
     * 自行生成一个问题，包括自行生成操作数
     * @return 一个算式问题
     */
    Arithmetic generator(QuestionParameterLimit questionParameterLimit);

}
