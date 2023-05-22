package com.five.enums;

public enum RoleEnum {

    STUDENT(0, "学生"),
    TEACHER(1, "教师"),
    ADMIN(1, "管理员");

    private final Integer role;
    private final String describe;

    RoleEnum(Integer role, String describe) {
        this.role = role;
        this.describe = describe;
    }

    public Integer value() {
        return role;
    }

    public String getDescribe() {
        return describe;
    }
}
