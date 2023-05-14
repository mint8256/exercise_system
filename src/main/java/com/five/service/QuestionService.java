package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.Question;

/**
 * (Question)表服务接口
 *
 * @author fly
 * @since 2023-05-09 15:38:13
 */
public interface QuestionService extends IService<Question> {

    /**
     * 根据questionListId删除对应的题目。
     */
    void removeByQuestionListId(Long questionListId);

}

