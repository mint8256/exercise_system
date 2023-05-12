package com.five.service.questions;

import com.five.entity.Question;
import com.five.entity.QuestionList;
import com.five.service.questions.model.QuestionParameterLimit;


import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/9 17:10
 */
public class DefaultQuestionListGenerator implements QuestionListGenerator{


    @Override
    public List<Question> gen(QuestionList questionList) {
        // 直接对不同的题目类型进行不同的创建
        QuestionParameterLimit questionParameterLimit = new QuestionParameterLimit();
        questionParameterLimit.setQuestionNum(questionList.getQuestionListNumber());
        questionParameterLimit.setMaxResultLimit(questionList.getResMax());
        questionParameterLimit.setMinResultLimit(questionList.getResMin());

        return null;
    }


}
