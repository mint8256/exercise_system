package com.five.service.questions;

import com.five.entity.Question;
import com.five.entity.QuestionList;
import com.five.enums.FormulaLimitEnum;
import com.five.enums.QuestionDifficultyEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.model.CacheMapThreadLocal;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.service.questions.paper.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/9 17:10
 */
@Service
public class DefaultQuestionListGenerator implements QuestionListGenerator{


    private static final List<PaperGenerator> PAPER_GENERATORS = new ArrayList<>();

    private final List<FormulaLimitEnum> formulaLimitEnumList = new ArrayList<>();

    public DefaultQuestionListGenerator(){
        formulaLimitEnumList.add(FormulaLimitEnum.QUESTION_RESULT_CHECK);
        formulaLimitEnumList.add(FormulaLimitEnum.NON_REPEATABLE);
        formulaLimitEnumList.add(FormulaLimitEnum.MUST_DIVISOR);
    }

    static {
        PAPER_GENERATORS.add(new SimpleAddPaperGenerator());
        PAPER_GENERATORS.add(new SimpleSubPaperGenerator());
        PAPER_GENERATORS.add(new SimpleMultiplyPaperGenerator());
        PAPER_GENERATORS.add(new SimpleDivisionPaperGenerator());
        PAPER_GENERATORS.add(new AddAndSubPaperGenerator());
        PAPER_GENERATORS.add(new MultiplyAndDivisionPaperGenerator());
        PAPER_GENERATORS.add(new MixtureOperationPaperGenerator());
    }

    @Override
    public List<Question> gen(QuestionList questionList) {

        // 直接对不同的题目类型进行不同的创建
        QuestionParameterLimit questionParameterLimit = new QuestionParameterLimit();
        questionParameterLimit.setQuestionNum(questionList.getQuestionCount());
        questionParameterLimit.setMaxResultLimit(questionList.getResMax());
        questionParameterLimit.setMinResultLimit(questionList.getResMin());

        // 处理用户非重复性判断的Map结构：ThreadLocal
        CacheMapThreadLocal.put();
        // 获取指定类型的题目列表生成器
        PaperGenerator paperGenerator = PAPER_GENERATORS.get(questionList.getType() - 1);
        // 通过生成器生成题目列表
        ArithmeticPaper arithmeticPaper = paperGenerator.generator(formulaLimitEnumList, questionParameterLimit);
        // 构造指定格式的题目列表并返回
        List<Question> generatorQuestions = new ArrayList<>();
        for (int i = 0; i < arithmeticPaper.getArithmetics().size(); i++) {
            Arithmetic arithmetic = arithmeticPaper.getArithmetics().get(i);
            Question question = new Question();
            question.setOrder(i + 1);
            question.setQuestionAnswer(arithmetic.getAnswer().toString());
            question.setStem(arithmetic.toString());
            generatorQuestions.add(question);
            // 设置分数
            if (questionList.getType() < 3){
                question.setScore(QuestionDifficultyEnum.A.getScore().doubleValue());
            }else if(questionList.getType() >= 3 && questionList.getType() <  6) {
                question.setScore(QuestionDifficultyEnum.B.getScore().doubleValue());
            }else{
                question.setScore(QuestionDifficultyEnum.C.getScore().doubleValue());

            }
        }
        // 清除用于重复性检查的Map对应的ThreadLocal防止内存泄露.
        CacheMapThreadLocal.clear();
        return generatorQuestions;
    }


}
