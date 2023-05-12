package com.five.service.questions;

import com.five.entity.Question;
import com.five.entity.QuestionList;

import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/9 16:57
 */
public interface QuestionListGenerator {

    /**
     * 根据QuestionList限制生成算式列表
     * @param questionList questionList限制
     * @return 算式列表
     */
    List<Question> gen(QuestionList questionList);

}
