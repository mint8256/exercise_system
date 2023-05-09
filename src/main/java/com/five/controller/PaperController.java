package com.five.controller;

import com.five.service.PaperService;
import com.five.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 14:39
 */
@RestController
@RequestMapping("paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @GetMapping("/{userId}")
    public R getAllPaperByUserId(@PathVariable("userId")Long userId){

//        List<> paperService.getAllPaperByUserId(userId);



        return R.success();
    }
}
