package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.UserQuestion;

/**
 * (UserQuestion)表服务接口
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
public interface UserQuestionService extends IService<UserQuestion> {

    /**
     * 创建用户对应的题目信息
     *
     * @param userPaperId    用户试卷id
     * @param paperId        用户试卷id
     * @param questionListId 试题列表id
     */
    void createUserQuestion(Long userPaperId, Long paperId, Long questionListId);
}

