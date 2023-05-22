package com.five.service.questions.model;


import com.five.enums.EqualsSymbolEnum;
import com.five.enums.OperationEnum;

import java.util.List;

/**
 * @description 算式抽象模型
 */
public class Arithmetic {

    /**
     * 操作数集合
     */
    private List<Integer> operateNums;
    /**
     * 操作符集合
     */
    private List<OperationEnum> operateSymbols;
    /**
     * 等于符
     */
    private EqualsSymbolEnum equalSymbol = EqualsSymbolEnum.ACCURATE;

    /**
     * 题目对应的答案
     */
    private Integer answer;

    public Arithmetic(){}

    public Arithmetic(List<Integer> operateNums, List<OperationEnum> operateSymbols){
        this.operateNums = operateNums;
        this.operateSymbols = operateSymbols;
    }

    public void setEqualSymbol(EqualsSymbolEnum equalSymbol){
        this.equalSymbol = equalSymbol;
    }

    public List<Integer> getOperateNums() {
        return operateNums;
    }

    public void setOperateNums(List<Integer> operateNums) {
        this.operateNums = operateNums;
    }

    public List<OperationEnum> getOperateSymbols() {
        return operateSymbols;
    }

    public void setOperateSymbols(List<OperationEnum> operateSymbols) {
        this.operateSymbols = operateSymbols;
    }

    public void setAnswer(Integer answer){
        this.answer = answer;
    }

    public Integer getAnswer(){
        return this.answer;
    }


    public EqualsSymbolEnum getEqualSymbol() {
        return equalSymbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (;i < operateNums.size()-1;i++){
            sb.append(operateNums.get(i));
            sb.append(operateSymbols.get(i).getOperateSymbol());
        }
        sb.append(operateNums.get(i));
        sb.append(equalSymbol.getEqualsSymbol());
        return sb.toString();
    }
}
