package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.QuestionDao;
import com.five.entity.Question;
import com.five.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * (Question)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {

}

