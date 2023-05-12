package com.five.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClazzVo {
    //班级id，主键
    private Long clazzId;
    //班级名称
    private String clazzName;
    //年级
    private String grade;
    //班级序号
    private Integer classNumber;
    //学校id
    private Long schoolId;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
    //班级人数
    private Long count;
    //班级编号
    private String clazzIdentifier;
}

