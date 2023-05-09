package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserQuestionDao;
import com.five.entity.UserQuestion;
import com.five.service.UserQuestionService;
import org.springframework.stereotype.Service;

/**
 * (UserQuestion)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Service("userQuestionService")
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionDao, UserQuestion> implements UserQuestionService {

}

