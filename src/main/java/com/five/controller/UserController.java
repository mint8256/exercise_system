package com.five.controller;

import com.five.aop.annotation.AuthVerify;
import com.five.enums.RoleEnum;
import com.five.service.UserService;
import com.five.vo.R;
import com.five.vo.StudentVo;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 20:14
 */
@RestController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @AuthVerify(roles = RoleEnum.TEACHER)
    public R<List<StudentVo>> batchImportStudents(@RequestPart("file") MultipartFile file) {

        // 获取教师的学校
        List<StudentVo> list = userService.batchImportStudents(file);

        return R.success("全部学生导入成功", list);
    }

}
