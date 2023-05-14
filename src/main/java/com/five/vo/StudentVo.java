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
 * @since 2023/5/9 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentVo {

    private Long userId;
    //用户名
    private String username;
    //真实姓名
    private String realName;
    //0：未知，1：男，2：女
    private Integer sex;
    //0：学生，1：教师，2：管理员
    private Integer role;
    //学校id，（为了方便生成编号）
    private String schoolName;
    //班级
    private String clazzName;
    //年级
    private Integer grade;
    //用户编号
    private String userIdentifier;
    //注册时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime signTime;
}
