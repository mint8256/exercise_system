package com.five.service.questions.paper;

import com.five.service.questions.chain.AbstractExecute;
import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.question.SimpleAddQuestionGenerator;

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
    protected ArithmeticPaper doGenerator(AbstractExecute executeChain, ArithmeticPaper arithmeticPaper) {
        return null;
    }
}
