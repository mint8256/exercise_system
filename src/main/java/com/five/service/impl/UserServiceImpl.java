package com.five.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.ClazzDao;
import com.five.dao.SchoolDao;
import com.five.dao.UserDao;
import com.five.entity.Clazz;
import com.five.entity.School;
import com.five.entity.User;
import com.five.entity.UserClazz;
import com.five.enums.RoleEnum;
import com.five.exception.BaseException;
import com.five.query.UserQuery;
import com.five.service.UserClazzService;
import com.five.service.UserService;
import com.five.util.*;
import com.five.vo.MyPage;
import com.five.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private UserClazzService userClazzService;
    @Resource
    private MapperFacade mapperFacade;

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
        User login = userDao.selectByName(StrUtil.trim(username));
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

        register.setUsername(StrUtil.trim(register.getUsername()));

        register.setPassword(PasswordManager.encryption(register.getPassword()));
        register.setRole(RoleEnum.TEACHER.getRole());

        Long schoolId = register.getSchoolId();
        Integer number = this.nextTeacherNumber(schoolId);
        String teacherIdentifier = IdentifierGenerator.genTeacherIdentifier(schoolId, number);
        register.setUserIdentifier(teacherIdentifier);

        userDao.insert(register);

        return register;
    }

    @Override
    public boolean usernameIsExist(String username) {
        return this.selectByName(username).getUserId() != null;
    }

    @Override
    public List<StudentVo> batchImportStudents(MultipartFile file) {

        Long userId = AuthUserContext.get().getUserId();

        //获取教师的学校信息，当作导入的学生的学校信息
        School school = this.getSchoolByUserId(userId);
        String schoolName = school.getSchoolName();

        // TODO 批量导入学生信息

        return null;
    }

    @Override
    public School getSchoolByUserId(Long userId) {

        User user = this.getById(userId);

        return schoolDao.selectById(user.getSchoolId());
    }

    @Override
    public Integer nextTeacherNumber(Long schoolId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getSchoolId, schoolId);
        queryWrapper.eq(User::getRole, RoleEnum.TEACHER.getRole());

        List<User> list = this.list(queryWrapper);
        return list.size() + 1;
    }

    @Override
    public MyPage<List<StudentVo>> getStuList(UserQuery userQuery) {

        MyPage<List<StudentVo>> myPage = new MyPage<>();

        List<StudentVo> voList = new ArrayList<>();
        Long clazzId = userQuery.getClazzId();

        // 如果班级id不为空，可以直接调用查询班级学生的，然后再从里面进行筛选
        if (clazzId != null) {

            voList = SpringContextUtil.getBean(ClazzServiceImpl.class).getStudentListById(clazzId);


            voList = voList.stream()
                    // 用户编号
                    .filter((vo) -> StrUtil.isNotBlank(userQuery.getUserIdentifier())
                            && MyStrUtil.equal(userQuery.getUserIdentifier(), vo.getUserIdentifier()))
                    // 用户名
                    .filter((vo) -> StrUtil.isNotBlank(userQuery.getUsername())
                            && MyStrUtil.equal(userQuery.getUsername(), vo.getUsername()))
                    // 真实姓名
                    .filter((vo) -> StrUtil.isNotBlank(userQuery.getRealName())
                            && MyStrUtil.equal(userQuery.getRealName(), vo.getRealName()))
                    // 性别
                    .filter((vo) -> userQuery.getSex() != null
                            && Objects.equals(vo.getSex(), userQuery.getSex())).collect(Collectors.toList());

            myPage.setTotal(voList.size());
            myPage.calTotalPage(voList.size(), userQuery.getSize());

            // 对结果进行分页处理
            List<StudentVo> sub = ListUtil.sub(voList, userQuery.getStart(), userQuery.getSize());

            myPage.setData(sub);

            return myPage;
        }

        Page<User> userPage = new Page<>();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(userQuery.getUserIdentifier()), User::getUserIdentifier, userQuery.getUserIdentifier());
        queryWrapper.eq(StrUtil.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername());
        queryWrapper.eq(StrUtil.isNotBlank(userQuery.getRealName()), User::getRealName, userQuery.getRealName());
        queryWrapper.eq(userQuery.getSex() != null, User::getSex, userQuery.getSex());


        userPage = userDao.selectPage(userPage, queryWrapper);

        int total = (int) userPage.getTotal();
        List<User> records = userPage.getRecords();

        List<StudentVo> vos = records.stream().map(this::userToVo).collect(Collectors.toList());

        myPage.setTotal(total);
        myPage.calTotalPage(total, userQuery.getSize());
        myPage.setData(vos);

        return myPage;
    }

    @Override
    public StudentVo userToVo(User user) {

        StudentVo vo = mapperFacade.map(user, StudentVo.class);

        UserClazz userClazz = userClazzService.getOneByUserId(user.getUserId());
        Clazz clazz = clazzDao.selectById(userClazz.getClazzId());

        vo.setClazzName(clazz.getClazzName());
        vo.setGrade(clazz.getGrade());

        return vo;
    }

    @Override
    public void deleteStudent(Long userId) {

        // 只需删除对应的用户班级关联信息即可
        userClazzService.deleteByUserId(userId);

    }

}

