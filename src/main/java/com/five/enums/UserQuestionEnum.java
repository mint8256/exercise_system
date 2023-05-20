package com.five.enums;

import lombok.Getter;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 17:19
 */
public enum UserQuestionEnum {
    TRUE(0, "对了"),
    FALSE(1, "错了"),
    NOT_WRITTEN(2, "未做"),
    ;
    private final Integer value;
    @Getter
    private final String desc;

    UserQuestionEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }
}
