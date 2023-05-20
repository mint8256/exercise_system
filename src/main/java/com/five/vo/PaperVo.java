package com.five.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.five.entity.Clazz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 13:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperVo {
    private Long paperId;
    //试卷名称
    private String paperName;
    //开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
    //持续时间
    private LocalDateTime duration;
    //题目列表id（来源于哪个题目列表）
    private Long questionListId;
    //题目数量
    private Integer questionCount;
    //试卷总分
    private Double score;
    //试卷编号
    private String paperIdentifier;
    //试卷难度
    private String paperDifficulty;
    // 试卷类型
    private Integer paperType;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
    //发布人id
    private Long userId;
    //试卷状态（0：未发布，1：已发布）
    private Integer status;
    //关联的班级信息（有可能关联多个班级）
    List<Clazz> clazzList;

}
