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
 * (QuestionList)表实体类
 *
 * @author fly
 * @since 2023-05-09 15:38:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionList{

    @TableId(type = IdType.AUTO)
    private Integer questionListId;
    //题目数量
    private Integer questionCount;
    //操作数最大数量
    private Integer optMax;
    //最大结果值
    private Integer resMax;
    //结果最小值
    private Integer resMin;
    //年级（如：2020）
    private Integer grade;
    // 题目列表难度（简单，中等，困难）
    private String questionListDifficulty;
    //学校id
    private Long schoolId;
    //题目列表序号
    private Integer questionListNumber;
    //题目列表编号
    private String questionListIdentifier;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
    //试卷列表的总分
    private Double score;
    // 题目列表类型
    private int type;
    //创建人
    private Long userId;
}

