package com.five.controller;

import com.five.aop.annotation.AuthVerify;
import com.five.enums.RoleEnum;
import com.five.service.AnalysisService;
import com.five.vo.PaperAnalysis;
import com.five.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/22 14:32
 */
@RestController
@RequestMapping("analysis")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("{paperId}")
    public R<List<PaperAnalysis>> paperAnalysis(@PathVariable("paperId") Long paperId) {

        List<PaperAnalysis> list = analysisService.analysis(paperId);

        return R.success(list);
    }

    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("/file/{paperId}")
    public Object paperDataFile(@PathVariable("paperId") Long paperId, HttpServletResponse response) throws IOException {
        analysisService.paperDataFile(paperId,response);
        return null;
    }
}
