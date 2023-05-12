package com.five.service.questions.question;



import com.five.enums.OperationEnum;
import com.five.service.questions.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description 生成一道简单的两个操作数的加法题目
 */
public class SimpleAddQuestionGenerator implements QuestionGenerator{

    private static final List<OperationEnum> OPERATION_ENUM_LIST = new ArrayList<>();

    static {
        OPERATION_ENUM_LIST.add(OperationEnum.ADD);
    }

    @Override
    public Question generator(List<Integer> operateNums) {
        // 生成一个题目（根据指定的操作数）
        Question question = new Question();
        question.setOperateNums(operateNums);
        question.setAnswer(operateNums.get(0) + operateNums.get(1));
        question.setOperateSymbols(OPERATION_ENUM_LIST);
        return question;
    }

    @Override
    public Question generator() {
        // 随机生成两个操作数
        Random random = new Random();
        int operateNum1 = random.nextInt(101);
        int operateNum2 = random.nextInt(101 - operateNum1);
        List<Integer> operatorNums = new ArrayList<>();
        operatorNums.add(operateNum1);
        operatorNums.add(operateNum2);
        return generator(operatorNums);
    }

}
