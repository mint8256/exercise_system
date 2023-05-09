package com.five.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * (School)表实体类
 *
 * @author fly
 * @since 2023-05-09 13:45:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class School{
    //学校id
    private Long schoolId;
    //学校名称
    private String schoolName;
    protected Serializable pkVal() {
        return this.schoolId;
    }
}

