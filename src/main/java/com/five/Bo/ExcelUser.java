package com.five.Bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/14 15:19
 */
@Data
public class ExcelUser {
    @ExcelProperty("学号")
    private String username;
    @ExcelProperty("真实姓名")
    private String realName;
    @ExcelProperty("性别")
    private String sexDesc;
}
