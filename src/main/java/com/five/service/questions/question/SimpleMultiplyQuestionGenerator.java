package com.five.service.questions.question;

import com.five.enums.OperationEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.util.ArithmeticUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description  生成一道简单的只包含两个操作数的乘法题目
 */
public class SimpleMultiplyQuestionGenerator implements QuestionGenerator {



    @Override
    public Arithmetic generator(List<Integer> operateNums) {
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.setOperateNums(operateNums);
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        for (int i = 0;i < operateNums.size() - 1;i++){
            operationSymbols.add(OperationEnum.MULTIPLY);
        }
        arithmetic.setOperateSymbols(operationSymbols);
        ArithmeticUtil.calculateAnswer(arithmetic);
        return arithmetic;
    }

    @Override
    public Arithmetic generator(QuestionParameterLimit questionParameterLimit) {
        Random random = new Random();
        int maxOperationNums = random.nextInt(4) + 2;
        List<Integer> operatorNums = new ArrayList<>();
        for (int i = 0; i < maxOperationNums; i++) {
            int operateNum = random.nextInt(questionParameterLimit.getMaxResultLimit() + 1);
            operatorNums.add(operateNum);
        }
        return generator(operatorNums);
    }

}
