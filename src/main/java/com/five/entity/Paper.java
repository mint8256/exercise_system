package com.five.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * (Paper)表实体类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Paper {
    //试卷id
    @TableId(type = IdType.AUTO)
    private Long paperId;
    //试卷名称
    private String paperName;
    //开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
    //持续时间(单位：s)
    private Long duration;
    //题目列表id（来源于哪个题目列表）
    private Long questionListId;
    //题目数量
    private Integer questionCount;
    //试卷总分
    private Double paperScore;
    //试卷编号
    private String paperIdentifier;
    //试卷难度
    private String paperDifficulty;
    //试卷类型( 0：练习试卷 1：时段试卷 )
    private Integer paperType;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
    //试卷状态（0：未发布，1：已发布）
    private Integer status;
    //创建人id
    private Long userId;
}

