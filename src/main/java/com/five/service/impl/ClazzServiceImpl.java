package com.five.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.ClazzDao;
import com.five.dao.UserClazzDao;
import com.five.dao.UserDao;
import com.five.entity.*;
import com.five.enums.RoleEnum;
import com.five.service.ClazzService;
import com.five.service.PaperClazzService;
import com.five.service.UserClazzService;
import com.five.util.AuthUserContext;
import com.five.util.IdentifierGenerator;
import com.five.util.SpringContextUtil;
import com.five.vo.ClazzVo;
import com.five.vo.StudentVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Clazz)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("clazzService")
public class ClazzServiceImpl extends ServiceImpl<ClazzDao, Clazz> implements ClazzService {

    @Resource
    private ClazzDao clazzDao;
    @Resource
    private UserClazzService userClazzService;

    @Resource
    private PaperClazzService paperClazzService;
    @Resource
    private UserDao userDao;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private UserClazzDao userClazzDao;

    @Override
    public List<ClazzVo> getClazzVoList() {

        List<Clazz> clazzList = getClazzList();

        return clazzList.stream().map(this::clazzToVo).collect(Collectors.toList());
    }

    @Override
    public List<Clazz> getClazzList() {
        Long userId = AuthUserContext.userId();

        List<UserClazz> userClazzList = userClazzService.getListByUserId(userId);
        Set<Long> ids = userClazzList.stream().map(UserClazz::getClazzId).collect(Collectors.toSet());

        return this.listByIds(ids);
    }

    @Override
    public List<StudentVo> getStudentListById(Long clazzId) {

        List<UserClazz> userClazzList = userClazzService.getListByClazzId(clazzId);

        // 此时是包含，教师的
        Set<Long> userIds = userClazzList.stream().map(UserClazz::getUserId).collect(Collectors.toSet());

        List<User> users = userDao.selectBatchIds(userIds);
        // 过滤掉老师
        users = users.stream().filter((u) -> u.getRole().equals(RoleEnum.STUDENT.value())).collect(Collectors.toList());

        return users.stream().map(SpringContextUtil.getBean(UserServiceImpl.class)::userToVo).collect(Collectors.toList());
    }

    @Override
    public List<StudentVo> getStudentListByName(String clazzName) {

        LambdaQueryWrapper<Clazz> clazzLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clazzLambdaQueryWrapper.eq(Clazz::getClazzName,clazzName);
        // 先找到classId
        Clazz clazz = clazzDao.selectOne(clazzLambdaQueryWrapper);
        if (clazz == null){
            return Collections.emptyList();
        }
        return getStudentListById(clazz.getClazzId());
    }

    @Override
    public Long count(Long clazzId) {
        LambdaQueryWrapper<UserClazz> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserClazz::getClazzId, clazzId);

        // 这里-1是为了减去这个老师的
        return userClazzService.count(queryWrapper) - 1;
    }

    @Override
    public boolean checkIsExist(String clazzName) {
        return this.getClazzByName(clazzName) != null;
    }

    @Override
    public Clazz getClazzByName(String clazzName) {

        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Clazz::getClazzName, StrUtil.trim(clazzName));

        return clazzDao.selectOne(queryWrapper);
    }

    @Override
    public void createClazz(Clazz clazz) {

        Long userId = AuthUserContext.userId();

        clazz.setClazzName(StrUtil.trim(clazz.getClazzName()));

        School school = SpringContextUtil.getBean(UserServiceImpl.class).getSchoolByUserId(userId);
        clazz.setSchoolId(school.getSchoolId());
        // 注意此处的grade应该是123456年级，需要转化为2020这种形式
        Integer grade = clazz.getGrade();
        Integer level = LocalDateTime.now().getYear() - grade;

        clazz.setGrade(level);

        Integer clazzNumber = this.nextClazzNumber(level);
        clazz.setClassNumber(clazzNumber);

        String clazzIdentifier = IdentifierGenerator.genClazzIdentifier(clazz);
        clazz.setClazzIdentifier(clazzIdentifier);

        clazzDao.insert(clazz);

        // 还要记得要增加班级关联信息
        UserClazz userClazz = new UserClazz();
        userClazz.setClazzId(clazz.getClazzId());
        userClazz.setUserId(userId);
        userClazzDao.insert(userClazz);
    }

    @Override
    public Integer nextClazzNumber(Integer grade) {

        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Clazz::getGrade, grade);

        List<Clazz> clazzList = this.list(queryWrapper);

        return clazzList.size() + 1;
    }

    @Override
    @Transactional
    public void deleteClazz(Long clazzId) {

        // 1. 删除此用户班级关联信息
        List<UserClazz> userClazzList = userClazzService.getListByClazzId(clazzId);
        for (UserClazz clazz : userClazzList) {
            userClazzService.removeById(clazz.getId());
        }

        // 2. 删除于试卷班级关联信息
        List<PaperClazz> paperClazzList = paperClazzService.getListByClazzId(clazzId);
        for (PaperClazz paperClazz : paperClazzList) {
            paperClazzService.removeById(paperClazz.getId());
        }

        // 3. 删除此班级
        this.removeById(clazzId);
    }

    @Override
    public List<ClazzVo> queryList(Clazz clazz) {

        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StrUtil.isNotBlank(clazz.getClazzName()), Clazz::getClazzName, clazz.getClazzName());
        queryWrapper.eq(clazz.getGrade() != null, Clazz::getGrade, clazz.getGrade());
        List<Clazz> clazzList = this.list(queryWrapper);

        return clazzList.stream().map(this::clazzToVo).collect(Collectors.toList());
    }

    @Override
    public ClazzVo clazzToVo(Clazz clazz) {
        ClazzVo vo = mapperFacade.map(clazz, ClazzVo.class);
        vo.setCount(this.count(vo.getClazzId()));
        return vo;
    }
}

