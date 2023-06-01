package com.five.enums;

import lombok.Getter;

/**
 * description: 试卷类型：（0：未开始，1：未做完，2：已做完，3：已全部完成）
 *
 * @author fly
 * @since 2023/5/15 16:48
 */

public enum UserPaperStatusEnum {
    ALL(-1,"全部"),
    NOT_STARTED(0, "未开始"),
    NOT_WRITTEN(1, "已经开始，但是学生还没有做"),
    UNCORRECTED(2, "学生已经做完，但是还没有批改"),
    COMPLETED(3, "已批改完毕，即全部完成"),
    ;
    private final Integer value;
    @Getter
    private final String desc;

    UserPaperStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }
}
