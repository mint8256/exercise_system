package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.QuestionList;

import java.util.List;

/**
 * (QuestionList)表服务接口
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
public interface QuestionListService extends IService<QuestionList> {

    /**
     * 根据可选属性生成对应的题目列表
     *
     * @param questionList 题目列表可选属性
     */
    void genQuestionList(QuestionList questionList);

    /**
     * 获取这个老师的全部题目列表
     */
    List<QuestionList> getAll();
}

