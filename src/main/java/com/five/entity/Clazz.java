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
 * (Clazz)表实体类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Clazz {
    //班级id，主键
    @TableId(type = IdType.AUTO)
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
    //班级编号
    private String clazzIdentifier;
}

