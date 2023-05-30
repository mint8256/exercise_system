package com.five.controller;

import cn.hutool.core.util.StrUtil;
import com.five.aop.annotation.AuthVerify;
import com.five.entity.UserPaper;
import com.five.entity.UserQuestion;
import com.five.enums.RoleEnum;
import com.five.query.UserPaperQuery;
import com.five.service.UserPaperService;
import com.five.vo.MyPage;
import com.five.vo.R;
import com.five.vo.UserPaperDetail;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 学生获取自己的试卷列表
     * @param baseQuery 分页参数
     * @return 试卷列表Vo
     */
    @Operation(summary = "学生获取自己的试卷列表")
    @GetMapping("/userPaper")
    public R<MyPage<List<UserPaper>>> getPaperList(UserPaperQuery baseQuery){

        return R.success(userPaperService.getPaperList(baseQuery));
    }

    @AuthVerify(roles = {RoleEnum.STUDENT, RoleEnum.TEACHER})
    @GetMapping("/{userPaperId}")
    public R<UserPaperDetail> getUserPaperDetail(@PathVariable("userPaperId") Long userPaperId) {

        UserPaperDetail userPaperDetail = userPaperService.getUserPaperDetail(userPaperId);

        return R.success(userPaperDetail);
    }



    /**
     * 获取学生的剩余考试时间
     * @param paperId 试卷id
     * @return 当前考试剩余时间
     */
    @Operation(summary = "学生获取当前考试的剩余时间")
    @GetMapping("/remainingTime/{paperId}")
    @AuthVerify(roles = {RoleEnum.STUDENT})
    public R<Integer> getRemainingTime(@PathVariable("paperId") Long paperId){
        return R.success(userPaperService.getUserPaperRemaining(paperId));
    }

    /**
     * 学生开始考试，设置考试状态
     * @param paperId 试卷id
     * @return 是否成功
     */
    @Operation(summary = "学生开始考试")
    @PutMapping("/startExam/{paperId}")
    @AuthVerify(roles = {RoleEnum.STUDENT})
    public R<Void> startExam(@PathVariable("paperId")Long paperId){
        userPaperService.startExam(paperId);
        return R.success();
    }

    /**
     * 学生提交试卷。
     * @return
     */
    @Operation(summary = "学生提交试卷")
    @PostMapping("/submitPaper/{paperId}")
    @AuthVerify(roles = {RoleEnum.STUDENT})
    public R<Void> submitPaper(@PathVariable("paperId")Long paperId){
        userPaperService.submitPaper(paperId);
        return R.success();
    }

    /**
     * 学生提交一道题目
     * @param userQuestion 题目作答信息
     * @return 是否成功
     */
    @Operation(summary = "学生提交一道题目")
    @PostMapping("/submitQuestion")
    @AuthVerify(roles = {RoleEnum.STUDENT})
    public R<Void> submitQuestion(UserQuestion userQuestion){
        userPaperService.submitQuestion(userQuestion);
        return R.success();
    }
}
