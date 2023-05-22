package com.five.service.questions.question;



import com.five.enums.OperationEnum;
import com.five.service.questions.QuestionListGenerator;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.util.ArithmeticUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description 生成一道简单的两个操作数的加法题目
 */
public class SimpleAddQuestionGenerator implements QuestionGenerator{



    @Override
    public Arithmetic generator(List<Integer> operateNums) {
        // 生成一个题目（根据指定的操作数）
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.setOperateNums(operateNums);
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        for (int i = 0;i < operateNums.size() - 1;i++){
            operationSymbols.add(OperationEnum.ADD);
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
