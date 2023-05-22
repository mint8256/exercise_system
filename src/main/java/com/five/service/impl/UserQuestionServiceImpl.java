package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserQuestionDao;
import com.five.entity.Question;
import com.five.entity.UserQuestion;
import com.five.enums.UserQuestionEnum;
import com.five.service.QuestionService;
import com.five.service.UserQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserQuestion)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Service("userQuestionService")
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionDao, UserQuestion> implements UserQuestionService {

    @Resource
    private QuestionService questionService;
    @Resource
    private UserQuestionDao userQuestionDao;

    @Override
    public void createUserQuestion(Long userPaperId, Long paperId, Long questionListId) {

        List<Question> questionList = questionService.getByQuestionListId(questionListId);

        questionList.forEach((question -> {

            UserQuestion uq = new UserQuestion();
            uq.setQuestionId(question.getQuestionId());
            uq.setUserPaperId(userPaperId);
            uq.setPaperId(paperId);
            uq.setQuestionScore(question.getScore());
            uq.setStatus(UserQuestionEnum.NOT_WRITTEN.value());
            userQuestionDao.insert(uq);

        }));

    }

    @Override
    public List<UserQuestion> getByUserPaperId(Long userPaperId) {

        LambdaQueryWrapper<UserQuestion> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserQuestion::getUserPaperId, userPaperId);

        return this.list(queryWrapper);
    }
}

