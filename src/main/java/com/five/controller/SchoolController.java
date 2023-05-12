package com.five.controller;

import com.five.entity.School;
import com.five.service.SchoolService;
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
 * @since 2023/5/9 19:00
 */
@RestController
@RequestMapping("school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @Operation(summary = "获取全部的学校信息")
    @GetMapping
    public R<List<School>> getSchoolList() {
        return R.success(schoolService.list());
    }

}
