package com.five.enums;

/**
 * @description 操作符类型
 */
public enum OperationEnum {
    ADD('+'),
    SUB('-'),
    MULTIPLY('x'),
    DIVISION('÷'),
    ;
    private final Character operateSymbol;

    OperationEnum(Character operateSymbol){
        this.operateSymbol = operateSymbol;
    }

    public Character getOperateSymbol() {
        return operateSymbol;
    }
}
