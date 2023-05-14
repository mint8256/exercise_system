package com.five.controller;

import com.five.aop.annotation.AuthVerify;
import com.five.enums.RoleEnum;
import com.five.query.UserQuery;
import com.five.service.UserService;
import com.five.vo.MyPage;
import com.five.vo.R;
import com.five.vo.StudentVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 20:14
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "批量导入学生信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("batch")
    public R<List<StudentVo>> batchImportStudents(@RequestPart("file") MultipartFile file) {

        // 获取教师的学校
        List<StudentVo> list = userService.batchImportStudents(file);

        return R.success("全部学生导入成功", list);
    }

    @Operation(summary = "获取该教师管理的学生信息，分页查询，并提供额外的查询条件")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("list")
    public R<MyPage<List<StudentVo>>> getStuList(UserQuery userQuery) {

        MyPage<List<StudentVo>> myPage = userService.getStuList(userQuery);

        return R.success(myPage);
    }

    @Operation(summary = "移除学生信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @DeleteMapping("/{userId}")
    public R<Void> deleteStudent(@PathVariable("userId")Long userId){

        userService.deleteStudent(userId);

        return R.success("移除成功");
    }
}
