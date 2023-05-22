package com.five;

import com.five.dao.UserDao;
import com.five.entity.Question;
import com.five.entity.QuestionList;
import com.five.service.questions.QuestionListGenerator;
import com.five.util.PasswordManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ExerciseSystemApplicationTests {

    @Resource
    UserDao userDao;

    @Resource
    private QuestionListGenerator questionListGenerator;

    @Test
    void userDaoTest(){

        System.out.println(userDao.selectByName("1"));

    }

    @Test
    void contextLoads() {
        System.out.println(PasswordManager.encryption("123456"));
    }

    @Test
    void testQuestionListGenerator(){
        long start = System.currentTimeMillis();
        QuestionList questionList = new QuestionList();
        questionList.setType(7);
        questionList.setResMin(20);
        questionList.setResMax(500);
        questionList.setQuestionListNumber(100);
        List<Question> gen = questionListGenerator.gen(questionList);
        System.out.println(gen.size());
        for (Question question : gen) {
            System.out.println(question);
        }
        System.out.println("耗时 : " + (System.currentTimeMillis() - start));
    }

}
