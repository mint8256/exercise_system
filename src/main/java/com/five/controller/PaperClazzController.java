package com.five.controller;

import com.five.service.PaperClazzService;
import com.five.vo.PaperClazzVo;
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
 * @since 2023/5/15 16:25
 */
@RestController
@RequestMapping("paperClazz")
public class PaperClazzController {


    @Resource
    private PaperClazzService paperClazzService;

    @Operation(summary = "根据条件获取试卷班级关联信息")
    @GetMapping
    public R<List<PaperClazzVo>> getPaperClazzVOList(String paperName,String clazzName){

        List<PaperClazzVo> paperClazzVoList = paperClazzService.getPaperClazzVOList(paperName,clazzName);
        return R.success();
    }


}
