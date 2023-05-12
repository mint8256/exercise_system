package com.five.service.questions.question;

import com.five.enums.OperationEnum;
import com.five.service.questions.model.Question;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description 生成一道简单的两个操作数的除法题目
 */
public class SimpleDivisionQuestionGenerator implements QuestionGenerator {

    /**
     * 操作符列表
     */
    private static final List<OperationEnum> OPERATION_ENUM_LIST = new ArrayList<>();

    static {
        OPERATION_ENUM_LIST.add(OperationEnum.DIVISION);
    }

    @Override
    public Question generator(List<Integer> operateNums) {
        Question question = new Question();
        question.setOperateNums(operateNums);
        question.setAnswer(operateNums.get(0) / operateNums.get(1));
        question.setOperateSymbols(OPERATION_ENUM_LIST);
        return question;
    }

    @Override
    public Question generator() {
        Random random = new Random();
        int operateNum1 = random.nextInt(100) + 1;
        int operateNum2 = random.nextInt(100) + 1;
        List<Integer> operatorNums = new ArrayList<>();
        operatorNums.add(operateNum1);
        operatorNums.add(operateNum2);
        return generator(operatorNums);
    }

}
