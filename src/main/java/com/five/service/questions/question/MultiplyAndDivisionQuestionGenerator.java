package com.five.service.questions.question;

import com.five.enums.OperationEnum;
import com.five.service.questions.model.Question;
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
    public Question generator(List<Integer> operateNums) {
        Question question = new Question();
        List<OperationEnum> operationSymbols = new ArrayList<>();
        // 随机设置几个操作符
        Random random = new Random();
        for (int i = 0;i < operateNums.size() - 1;i++){
            int index = random.nextInt(2);
            operationSymbols.add(OPERATION_ENUM_LIST.get(index));
        }
        question.setOperateNums(operateNums);
        question.setOperateSymbols(operationSymbols);
        calculateAnswer(question);
        return question;
    }


    /**
     * 计算算式的结果
     * @param question 问题
     */
    private void calculateAnswer(Question question){
        List<Integer> operateNums = question.getOperateNums();
        List<OperationEnum> operateSymbols = question.getOperateSymbols();
        Integer answer = operateNums.get(0);
        for (int i = 1;i < operateNums.size();i++){
            answer = ArithmeticUtil.getOperateResult(answer,operateSymbols.get(i - 1),operateNums.get(i));
        }
        question.setAnswer(answer);
    }

    @Override
    public Question generator() {
        Random random = new Random();
        int num = random.nextInt(6) + 2;
        List<Integer> operatorNums = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            operatorNums.add(random.nextInt(100));
        }
        return generator(operatorNums);
    }
}
