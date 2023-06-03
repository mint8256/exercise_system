package com.five.service;

import com.five.vo.PaperAnalysis;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/22 14:33
 */
public interface AnalysisService {

    /**
     * 对试卷的成绩进行分析
     */
    List<PaperAnalysis> analysis(Long paperId);

    /**
     * 获取试卷的成绩导出
     *
     * @param paperId  试卷id
     * @param response
     * @return 文件
     */
    Void paperDataFile(Long paperId, HttpServletResponse response) throws IOException;
}
