package com.five.service.impl;

import com.five.entity.*;
import com.five.enums.UserPaperStatusEnum;
import com.five.service.*;
import com.five.vo.PaperAnalysis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/22 14:33
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private PaperClazzService paperClazzService;
    @Resource
    private UserClazzService userClazzService;
    @Resource
    private PaperService paperService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private UserPaperService userPaperService;

    @Override
    public List<PaperAnalysis> analysis(Long paperId) {

        //1. 根据试卷获取全部的班级信息
        List<Long> clazzIds = paperClazzService.getListByPaperId(paperId).stream().map(PaperClazz::getClazzId).collect(Collectors.toList());
        //2. 根据班级信息获取全部的学生
        List<PaperAnalysis> paperAnalysList = new ArrayList<>();
        for (Long clazzId : clazzIds) {
            PaperAnalysis paperAnalysis = new PaperAnalysis();

            Clazz clazz = clazzService.getById(clazzId);
            paperAnalysis.setClazzName(clazz.getClazzName());
            Paper paper = paperService.getById(paperId);
            paperAnalysis.setPaperName(paper.getPaperName());

            List<Long> userIds = userClazzService.getListByClazzId(clazzId).stream().map(UserClazz::getUserId).collect(Collectors.toList());
            //3. 根据user和paperId找到每个班的做的试卷,要过滤，只取已做完的
            List<UserPaper> userPaperList = userPaperService.getUserPaperList(paperId, userIds);
            //排序后的分数列表
            List<Double> scoreList = userPaperList.stream().filter(up -> up.getStatus() >= UserPaperStatusEnum.UNCORRECTED.value()).map(UserPaper::getUserScore).sorted().collect(Collectors.toList());
            //4. 计算每个班的最高分、最低分、平均分、全部成绩、总人数
            //4.1 最高分
            Double maxScore = 0.0;
            Double minScore = 0.0;
            Double avgScore = 0.0;
            if (scoreList.size() > 0) {
                maxScore = scoreList.get(0);
                minScore = scoreList.get(scoreList.size() - 1);
                avgScore = scoreList.stream().collect(Collectors.averagingDouble((s) -> s));
            }
            paperAnalysis.setAllScore(scoreList);
            paperAnalysis.setMinScore(minScore);
            paperAnalysis.setMaxScore(maxScore);
            paperAnalysis.setAvgScore(avgScore);

            //进行A、B、C、D、E统计
            Map<Character, Long> countMap = scoreList.stream()
                    .collect(Collectors.groupingBy(this::getGrade, Collectors.counting()));

            paperAnalysis.setA(countMap.get('A'));
            paperAnalysis.setB(countMap.get('B'));
            paperAnalysis.setC(countMap.get('C'));
            paperAnalysis.setD(countMap.get('D'));
            paperAnalysis.setE(countMap.get('E'));

            paperAnalysis.setTotal(scoreList.size());
        }
        return paperAnalysList;
    }

    private char getGrade(double score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'E';
        }
    }
}
