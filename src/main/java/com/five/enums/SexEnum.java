package com.five.enums;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/14 13:42
 */
public enum SexEnum {
    UNKNOWN(0, "未知"),
    WOMEN(1, "女"),
    MAN(2, "男"),
    ;

    private final Integer value;
    private final String desc;

    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }
}
