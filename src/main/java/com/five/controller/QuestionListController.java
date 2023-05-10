package com.five.controller;

import com.five.aop.annotation.AuthVerify;
import com.five.entity.QuestionList;
import com.five.enums.RoleEnum;
import com.five.service.QuestionListService;
import com.five.vo.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 20:43
 */
@RequestMapping("questionList")
@RestController
public class QuestionListController {

    @Resource
    private QuestionListService questionListService;

    @Operation(summary = "根据一些属性生成对应的题目列表，生成题目列表")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping
    public R<Void> genQuestionList(@RequestBody QuestionList questionList) {
        questionListService.genQuestionList(questionList);
        return R.success("题目列表生成成功");
    }

    @Operation(summary = "获取全部题目列表")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping
    public R<List<QuestionList>> getAll() {
        List<QuestionList> list = questionListService.getAll();
        return R.success(list);
    }

    @Operation(summary = "删除题目列表列表")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @DeleteMapping("{questionListId}")
    public R<Void> delete(@PathVariable("questionListId") Long questionListId) {
        questionListService.removeById(questionListId);
        return R.success("删除成功");
    }

}
