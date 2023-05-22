package com.five.vo;

import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/22 14:23
 */
@Data
public class PaperAnalysis {

    // 班级名称
    private String clazzName;
    // 试卷名称
    private String paperName;

    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    // 这个班全部成绩
    private List<Double> allScore;
    //[90,100]
    private Long A;
    //[80,90)
    private Long B;
    //[70,80)
    private Long C;
    //[60,70)
    private Long D;
    //(0,60)
    private Long E;
    // 总人数
    private Integer total;

}
