package com.five.service;

import com.five.vo.PaperAnalysis;

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

}
