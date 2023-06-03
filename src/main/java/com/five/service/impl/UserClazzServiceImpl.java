package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserClazzDao;
import com.five.entity.UserClazz;
import com.five.service.UserClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户-班级关联表，主要是针对于老师多个班的情况(UserClazz)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("userClassService")
public class UserClazzServiceImpl extends ServiceImpl<UserClazzDao, UserClazz> implements UserClazzService {

    @Resource
    private UserClazzDao userClazzDao;

    @Override
    public List<UserClazz> getListByUserId(Long userId) {

        LambdaQueryWrapper<UserClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClazz::getUserId, userId);
        return userClazzDao.selectList(queryWrapper);
    }


    @Override
    public UserClazz getOneByUserId(Long userId) {
        LambdaQueryWrapper<UserClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClazz::getUserId, userId);
        List<UserClazz> userClazzList = userClazzDao.selectList(queryWrapper);

        if (userClazzList.isEmpty()) return new UserClazz();
        else return userClazzList.get(0);
    }

    @Override
    public List<UserClazz> getListByClazzId(Long clazzId) {

        LambdaQueryWrapper<UserClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClazz::getClazzId, clazzId);
        return userClazzDao.selectList(queryWrapper);
    }


    @Override
    public void deleteByUserId(Long userId) {

        LambdaQueryWrapper<UserClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserClazz::getUserId, userId);
        this.remove(queryWrapper);

    }
}

