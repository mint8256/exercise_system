package com.five.enums;

import lombok.Getter;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 16:50
 */
public enum PaperStatusEnum {
    NOT_RELEASE(0,"未发布"),
    RELEASED(1,"已发布"),
    ;
    private final Integer value;
    @Getter
    private final String desc;

    PaperStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public Integer value(){
        return this.value;
    }
}
