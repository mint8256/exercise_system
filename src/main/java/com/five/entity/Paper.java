package com.five.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Paper{
    //试卷id
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
    //试卷编号
    private String paperIdentifier;
    //试卷类型( 0：练习试卷 1：时段试卷 )
    private Integer paperType;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
}

