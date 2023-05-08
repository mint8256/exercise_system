package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-05-08 14:13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionList{

    @TableId(type = IdType.AUTO)
    private Integer questionListId;
    //题目数量
    private Integer questionNum;
    //操作数最大数量
    private Integer optMax;
    //最大结果值
    private Integer resMax;
    //年纪
    private Integer grade;
    //学校id
    private Long schoolId;
    //题目列表序号
    private Integer questionListNumber;
    //题目列表编号
    private String questionListIdentifier;
    //创建时间
    private LocalDateTime signTime;
}

