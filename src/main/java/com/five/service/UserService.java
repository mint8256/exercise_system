package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.School;
import com.five.entity.User;
import com.five.query.UserQuery;
import com.five.vo.MyPage;
import com.five.vo.StudentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     *
     * @param register 进行注册的用户
     * @return 注册成功的用户
     */
    User register(User register);

    /**
     * 用户判断用户名是否唯一
     *
     * @param username 用户名
     * @return 是否唯一
     */
    boolean usernameIsExist(String username);

    /**
     * 批量导入学生信息
     *
     * @param file      学生信息文件
     * @param clazzName 班级名称
     */
    void batchImportStudents(MultipartFile file, String clazzName) throws IOException;

    /**
     * 根据用户id查询其学校名称
     */
    School getSchoolByUserId(Long userId);

    /**
     * 查询当前学校的下一个教师编号
     *
     * @param schoolId 根据学校来计算的
     * @return 下一个教师编号
     */
    Integer nextTeacherNumber(Long schoolId);

    /**
     * 根据条件分业务获取学生信息
     *
     * @param userQuery 查询条件
     */
    MyPage<List<StudentVo>> getStuList(UserQuery userQuery);

    /**
     * 用户转换为studentVo
     *
     * @param user 用户
     * @return vo对象
     */
    StudentVo userToVo(User user);

    /**
     * 移除学生信息
     *
     * @param userId 用户id
     */
    void deleteStudent(Long userId);

    /**
     * 查询个人信息
     */
    StudentVo self();

    /**
     * 获取班级内的学生的下一个序号,由于肯定会有一个老师，所以就是从1开始的
     *
     * @param clazzId 班级id
     */
    Integer nextStuNumber(Long clazzId);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 判断该userId是否是学生
     */
    boolean isStudent(Long userId);
}

