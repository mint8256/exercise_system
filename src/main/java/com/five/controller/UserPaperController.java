package com.five.controller;

import cn.hutool.core.util.StrUtil;
import com.five.aop.annotation.AuthVerify;
import com.five.entity.UserPaper;
import com.five.enums.RoleEnum;
import com.five.query.UserPaperQuery;
import com.five.service.UserPaperService;
import com.five.vo.MyPage;
import com.five.vo.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 15:37
 */
@RestController
@RequestMapping("userPaper")
public class UserPaperController {

    @Resource
    private UserPaperService userPaperService;

    @Operation(summary = "根据查询获取答卷列表")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("list")
    public R<MyPage<List<UserPaper>>> getUserPaperList(UserPaperQuery userPaperQuery) {

        if (StrUtil.isBlank(userPaperQuery.getPaperName())) return R.fail("试卷名不能为空");
        if (StrUtil.isBlank(userPaperQuery.getClazzName())) return R.fail("班级名不能为空");

        MyPage<List<UserPaper>> myPage = userPaperService.getUserPaperList(userPaperQuery);

        return R.success(myPage);
    }

}
