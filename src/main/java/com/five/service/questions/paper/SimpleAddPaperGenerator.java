package com.five.service.questions.paper;

import com.five.service.questions.chain.AbstractExecute;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.service.questions.question.SimpleAddQuestionGenerator;

import java.util.Random;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/12 23:09
 */
public class SimpleAddPaperGenerator extends AbstractPaperGenerator{

    public SimpleAddPaperGenerator(){
        generators.add(new SimpleAddQuestionGenerator());
    }

    @Override
    protected ArithmeticPaper doGenerator(AbstractExecute executeChain, ArithmeticPaper arithmeticPaper, QuestionParameterLimit questionParameterLimit) {
        Random random = new Random();
        while (true){
            // 随机生成一个类别的算式
            int index = random.nextInt(generators.size());
            Arithmetic arithmetic = generators.get(index).generator(questionParameterLimit);
            // 对生成的算式
            ExecuteResult executeResult = executeChain.execute(arithmetic);
            // 达到指定的题目数量终止生成步骤。
            if (executeResult.isMaxNum()){
                break;
            }
            // 如果题目合法加入到试卷的题目列表中
            if (executeResult.isLegal()){
                putLegalQuestion(arithmeticPaper, arithmetic);
            }
        }
        return arithmeticPaper;
    }
}
