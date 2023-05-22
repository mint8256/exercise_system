package com.five;

import com.five.entity.User;
import com.five.enums.RoleEnum;
import com.five.enums.SexEnum;
import com.five.service.UserService;
import com.five.vo.StudentVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/14 13:38
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    public void test1() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        user.setSchoolId(1L);
        user.setSex(SexEnum.WOMEN.value());
        user.setRealName("啦啦");
        user.setRole(RoleEnum.TEACHER.value());
        System.out.println(user);
        userService.register(user);
        System.out.println(user);
    }

    @Test
    public void test2() {
        String name = "1";
        String password = "1";
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        User login = userService.login(user);
        StudentVo studentVo = userService.userToVo(login);
        System.out.println(login);
    }
}
