package com.five.service.questions.question;


import com.five.enums.OperationEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.util.ArithmeticUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description 生成一道复杂的包含多个操作数的加减混合运算
 */
public class AddAndSubQuestionGenerator implements QuestionGenerator{

    /**
     * 操作符列表
     */
    private static final List<OperationEnum> OPERATION_ENUM_LIST = new ArrayList<>();

    static {
        OPERATION_ENUM_LIST.add(OperationEnum.ADD);
        OPERATION_ENUM_LIST.add(OperationEnum.SUB);
    }


    @Override
    public Arithmetic generator(List<Integer> operateNums) {
        Arithmetic arithmetic = new Arithmetic();
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        Random random = new Random();
        // 随机放入指定数量的操作符
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
        int num = random.nextInt(7) + 2;
        List<Integer> operatorNums = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            operatorNums.add(random.nextInt(questionParameterLimit.getMaxResultLimit()));
        }
        return generator(operatorNums);
    }
}
