package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.QuestionDao;
import com.five.dao.QuestionListDao;
import com.five.entity.QuestionList;
import com.five.service.QuestionListService;
import com.five.util.AuthUserContext;
import com.five.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QuestionList)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Service("questionListService")
public class QuestionListServiceImpl extends ServiceImpl<QuestionListDao, QuestionList> implements QuestionListService {

    @Resource
    private QuestionListDao questionListDao;

    @Resource
    private QuestionDao questionDao;

    @Override
    public void genQuestionList(QuestionList questionList) {

        // TODO 根据题目列表属性生成需要的题目list

    }

    @Override
    public List<QuestionList> getAll() {

        Long userId = AuthUserContext.userId();

        LambdaQueryWrapper<QuestionList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionList::getUserId, userId);

        return this.list(queryWrapper);
    }

    @Override
    public void delete(Long questionListId) {

        // 首先删除其对应的试题列表中包含的题目
        SpringContextUtil.getBean(QuestionServiceImpl.class).removeByQuestionListId(questionListId);

        this.removeById(questionListId);

    }
}

