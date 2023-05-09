package com.five;

import com.five.dao.UserDao;
import com.five.util.PasswordManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExerciseSystemApplicationTests {

    @Resource
    UserDao userDao;

    @Test
    void userDaoTest(){

        System.out.println(userDao.selectByName("1"));

    }

    @Test
    void contextLoads() {
        System.out.println(PasswordManager.encryption("123456"));
    }

}
