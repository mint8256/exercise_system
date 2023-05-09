package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.ClazzDao;
import com.five.dao.SchoolDao;
import com.five.dao.UserClazzDao;
import com.five.dao.UserDao;
import com.five.entity.School;
import com.five.entity.User;
import com.five.exception.BaseException;
import com.five.service.UserService;
import com.five.util.AuthUserContext;
import com.five.util.PasswordManager;
import com.five.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * (User)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private SchoolDao schoolDao;
    @Resource
    private ClazzDao clazzDao;
    @Resource
    private UserClazzDao classDao;

    @Override
    public User login(User login) {

        User user = this.selectByName(login.getUsername(), true);

        log.debug("查询到的用户信息为：{}", user);

        Optional.ofNullable(user.getUserId()).orElseThrow(() -> new BaseException("用户名错误"));

        boolean checkPass = PasswordManager.checkPass(login.getPassword(), user.getPassword());

        if (!checkPass) throw new BaseException("密码错误");

        user.setPassword("");

        return user;
    }

    @Override
    public User selectByName(String username, boolean needPassword) {
        User login = userDao.selectByName(username);
        User user = Optional.ofNullable(login).orElse(new User());
        if (!needPassword) {
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User selectByName(String username) {
        return selectByName(username, false);
    }

    @Override
    public User register(User register) {

        //TODO 要进行计算用户的编号

        userDao.insert(register);

        return register;
    }

    @Override
    public boolean usernameIsUnique(String username) {
        return this.selectByName(username).getUserId() == null;
    }

    @Override
    public List<StudentVo> batchImportStudents(MultipartFile file) {

        Long userId = AuthUserContext.get().getUserId();

        User user = userDao.selectById(userId);

        //获取教师的学校信息，当作导入的学生的学校信息
        School school = schoolDao.selectById(user.getSchoolId());
        String schoolName = school.getSchoolName();

        // TODO 批量导入学生信息


        return null;
    }

}

