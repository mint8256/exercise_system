package com.five.enums;

/**
 * @description 等号类型
 */
public enum EqualsSymbolEnum {

    ACCURATE('='),
    APPROXIMATELY('≈'),
    ;

    private final Character equalsSymbol;

    EqualsSymbolEnum(Character equalsSymbol){
        this.equalsSymbol = equalsSymbol;
    }

    public Character getEqualsSymbol(){
        return this.equalsSymbol;
    }

}
