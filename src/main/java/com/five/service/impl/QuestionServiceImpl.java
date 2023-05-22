package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.QuestionDao;
import com.five.entity.Question;
import com.five.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Arithmetic)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:38:13
 */
@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Override
    public void removeByQuestionListId(Long questionListId) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getQuestionListId,questionListId);

        questionDao.delete(queryWrapper);
    }

    @Override
    public List<Question> getByQuestionListId(Long questionListId) {


        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getQuestionListId,questionListId);

       return questionDao.selectList(queryWrapper);
    }
}

