package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserClassDao;
import com.five.entity.UserClass;
import com.five.service.UserClassService;
import org.springframework.stereotype.Service;

/**
 * 用户-班级关联表，主要是针对于老师多个班的情况(UserClass)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("userClassService")
public class UserClassServiceImpl extends ServiceImpl<UserClassDao, UserClass> implements UserClassService {

}

