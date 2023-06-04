package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.PaperDao;
import com.five.dao.QuestionDao;
import com.five.dao.QuestionListDao;
import com.five.entity.Question;
import com.five.entity.QuestionList;
import com.five.service.QuestionListService;
import com.five.service.UserService;
import com.five.service.questions.QuestionListGenerator;
import com.five.util.AuthUserContext;
import com.five.util.IdentifierGenerator;
import com.five.util.SpringContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private QuestionListGenerator questionListGenerator;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private PaperDao paperDao;

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public void genQuestionList(QuestionList questionList) {
        //  根据题目列表属性生成需要的题目list
        Long userId = AuthUserContext.userId();
        Long schoolId = userService.getSchoolByUserId(userId).getSchoolId();

        questionList.setUserId(userId);
        questionList.setSchoolId(schoolId);
        Integer nextQuestionListNumber = this.getNextQuestionListNumber();
        questionList.setQuestionListNumber(nextQuestionListNumber);
        String questionListIdentifier = IdentifierGenerator.genQuestionListIdentifier(schoolId, questionList.getGrade(), nextQuestionListNumber);
        questionList.setQuestionListIdentifier(questionListIdentifier);
        questionListDao.insert(questionList);
        List<Question> questions = questionListGenerator.gen(questionList);
        double totalScore = 0;
        for (Question question : questions) {
            question.setQuestionListId(questionList.getQuestionListId().longValue());
            totalScore += question.getScore();
        }
        questionList.setScore(totalScore);
        questionListDao.updateById(questionList);
        questionDao.insertBatch(questions);
    }

    @Override
    public List<QuestionList> getAll(Integer type) {

        Long userId = AuthUserContext.userId();

        LambdaQueryWrapper<QuestionList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionList::getUserId, userId);
        if (type != 0){
            queryWrapper.eq(QuestionList::getType,type);
        }

        return this.list(queryWrapper);
    }

    @Override
    public void delete(Long questionListId) {

        //1. 首先删除其对应的试题列表中包含的题目
        SpringContextUtil.getBean(QuestionServiceImpl.class).removeByQuestionListId(questionListId);

        //2. 然后删除题目列表对应试卷
        //TODO 删除题目列表时是否要删除对应的试卷以及用户的做题记录以及对应的试卷班级关联信息？

        this.removeById(questionListId);

    }

    @Override
    public Integer getNextQuestionListNumber() {

        Long schoolId = userService.getSchoolByUserId(AuthUserContext.userId()).getSchoolId();

        // 通过school获取其下一个试卷序号
        LambdaQueryWrapper<QuestionList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionList::getSchoolId, schoolId);

        List<QuestionList> list = this.list(queryWrapper);
        return list.size() + 1;
    }
}

