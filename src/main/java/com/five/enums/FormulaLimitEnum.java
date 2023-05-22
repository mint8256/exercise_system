package com.five.enums;

/**
 * @description 对问题或者试卷的限制枚举
 */
public enum FormulaLimitEnum {

    NON_REPEATABLE(1,"不能有重复的算式！"),
    MUST_DIVISOR(2,"除法必须能被整除！"),
    QUESTION_RESULT_CHECK(3,"结果大小限制"),
    NUM_LIMIT(4,"题目数量限制"),
    MULTIPlY_LIMIT(4,"复杂的题目数量限制"),
    ;

    /**
     * 限制编号
     */
    private final Integer limitCode;
    /**
     * 限制描述
     */
    private final String limitDesc;

    FormulaLimitEnum(Integer limitCode,String limitDesc){
        this.limitCode = limitCode;
        this.limitDesc = limitDesc;
    }

    public Integer getLimitCode() {
        return limitCode;
    }

    public String getLimitDesc() {
        return limitDesc;
    }

    @Override
    public String toString() {
        return "FormulaLimitEnum{" +
                "limitCode=" + limitCode +
                ", limitDesc='" + limitDesc + '\'' +
                '}';
    }
}
