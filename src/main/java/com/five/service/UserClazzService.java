package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.UserClazz;

import java.util.List;

/**
 * 用户-班级关联表，主要是针对于老师多个班的情况(UserClazz)表服务接口
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
public interface UserClazzService extends IService<UserClazz> {

    /**
     * 根据用户id获取其列表
     *
     * @param userId 用户id
     * @return 对应的用户班级关联信息列表
     */
    List<UserClazz> getListByUserId(Long userId);


    /**
     * 根据用户id获取其班级信息
     *
     * @param userId 用户id
     * @return 对应的用户班级关联信息
     */
    UserClazz getOneByUserId(Long userId);

    /**
     * 根据班级id获取其列表
     *
     * @param clazzId 班级
     * @return 对应的用户班级关联信息列表
     */
    List<UserClazz> getListByClazzId(Long clazzId);

    /**
     * 根据userId删除其对应的关联关系
     * @param userId 用户id
     */
    void deleteByUserId(Long userId);

}

