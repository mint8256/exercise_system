package com.five.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @description
 * @Author cxk
 * @Date 2023/6/3 15:12
 */
@Getter
@Setter
@EqualsAndHashCode
public class PaperAnalysisFile {
    @ExcelProperty("学号")
    private String studentNumber;
    @ExcelProperty("姓名")
    private String studentName;
    @ExcelProperty("分数")
    private Double score;
    @ExcelProperty("正确个数")
    private Integer correctNum;
    @ExcelProperty("正确率")
    private Double correctRatio;
}
