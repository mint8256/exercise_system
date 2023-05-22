package com.five.service.questions.question;

import com.five.enums.OperationEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.util.ArithmeticUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description 生成一道复杂的，包含多个操作数的乘除混合运算
 */
public class MultiplyAndDivisionQuestionGenerator implements QuestionGenerator{

    private static final List<OperationEnum> OPERATION_ENUM_LIST = new ArrayList<>();

    static {
        OPERATION_ENUM_LIST.add(OperationEnum.MULTIPLY);
        OPERATION_ENUM_LIST.add(OperationEnum.DIVISION);
    }

    @Override
    public Arithmetic generator(List<Integer> operateNums) {
        Arithmetic arithmetic = new Arithmetic();
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        Random random = new Random();
        for (int i = 0;i < operateNums.size() - 1;i++){
            int index = random.nextInt(2);
            operationSymbols.add(OPERATION_ENUM_LIST.get(index));
        }
        arithmetic.setOperateNums(operateNums);
        arithmetic.setOperateSymbols(operationSymbols);
        ArithmeticUtil.calculateAnswer(arithmetic);
        return arithmetic;
    }


    @Override
    public Arithmetic generator(QuestionParameterLimit questionParameterLimit) {
        Random random = new Random();
        int num = random.nextInt(4) + 2;
        List<Integer> operatorNums = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            operatorNums.add(random.nextInt(questionParameterLimit.getMaxResultLimit()));
        }
        return generator(operatorNums);
    }
}
