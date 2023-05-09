package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.User;
import com.five.vo.StudentVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     */
    User login(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username     用户名
     * @param needPassword 是否需要密码
     * @return 查到的用户
     */
    User selectByName(String username, boolean needPassword);

    /**
     * 根据用户宁查询用户, 其中密码默认置空
     */
    User selectByName(String username);

    /**
     * 用户注册
     * @param register 进行注册的用户
     * @return 注册成功的用户
     */
    User register(User register);

    /**
     * 用户判断用户名是否唯一
     * @param username 用户名
     * @return 是否唯一
     */
    boolean usernameIsUnique(String username);

    /**
     * 批量导入学生信息
     * @param file 学生信息文件
     * @return 导入的血学生信息
     */
    List<StudentVo> batchImportStudents(MultipartFile file);
}

