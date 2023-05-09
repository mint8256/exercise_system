package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.QuestionListDao;
import com.five.entity.QuestionList;
import com.five.service.QuestionListService;
import org.springframework.stereotype.Service;

/**
 * (QuestionList)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Service("questionListService")
public class QuestionListServiceImpl extends ServiceImpl<QuestionListDao, QuestionList> implements QuestionListService {

}

