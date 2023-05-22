package com.five.vo;

import lombok.Getter;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 14:39
 */
@Getter
public enum RCodeEnum {

    SUCCESS(200, "SUCCESS"),
    FAIL(500, "FAIL"),
    FOREIGN_ERROR(1007, "外键异常"),
    PARAM_WRONG(1001, "错误的参数!"),
    INVALID_TOKEN(1002, "用户校验失败！"),
    NO_ACCESS(1003, "当前操作没有权限！"),
    VERIFICATION_ERROR(1004, "验证码错误！"),
    PASSWORD_NOT_STRONG(1006, "密码强度不够！"),
    PASSWORD_ERROR(1007, "密码错误！"),
    LOGIN_FAIL(1008, "账号或密码错误！"),
    EMAIL_NOT_EXIST(1009, "该邮箱暂未注册！"),
    TOKEN_NOT_EXIST(1010, "token 为空！"),
    USERNAME_IS_EXIST(1011, "用户名已经存在，换个用户名试试"),
    PAPER_NAME_IS_EXIST(1012, "试卷名称已经存在，换个试卷名试试"),
    ;

    private final Integer code;
    private final String msg;

    RCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
