package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserDao;
import com.five.entity.User;
import com.five.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

