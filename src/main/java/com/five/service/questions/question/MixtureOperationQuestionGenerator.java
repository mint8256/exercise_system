package com.five.service.questions.question;

import com.five.enums.OperationEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.util.ArithmeticUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/15 20:33
 */
public class MixtureOperationQuestionGenerator implements QuestionGenerator{

    private static final List<OperationEnum> OPERATION_ENUM_LIST = new ArrayList<>();

    static {
        OPERATION_ENUM_LIST.add(OperationEnum.SUB);
        OPERATION_ENUM_LIST.add(OperationEnum.ADD);
        OPERATION_ENUM_LIST.add(OperationEnum.MULTIPLY);
        OPERATION_ENUM_LIST.add(OperationEnum.DIVISION);
    }

    @Override
    public Arithmetic generator(List<Integer> operateNums) {
        Arithmetic arithmetic = new Arithmetic();
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        Random random = new Random();
        // 随机放入指定数量的操作符
        for (int i = 0;i < operateNums.size() - 1;i++){
            int index = random.nextInt(OPERATION_ENUM_LIST.size());
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
        int num = random.nextInt(8) + 2;
        List<Integer> operatorNums = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            operatorNums.add(random.nextInt(100));
        }
        return generator(operatorNums);
    }
}
