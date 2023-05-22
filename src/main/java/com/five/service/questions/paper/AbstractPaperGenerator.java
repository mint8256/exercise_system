package com.five.service.questions.paper;


import com.five.enums.FormulaLimitEnum;
import com.five.service.questions.chain.*;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.model.QuestionParameterLimit;
import com.five.service.questions.question.QuestionGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 抽象试卷生成器 ，使用模板方法屏蔽限制链对子类的影响
 */
public abstract class AbstractPaperGenerator implements PaperGenerator {

    /**
     * 保存限制条件和对应的限制执行链的Map
     */
    public Map<FormulaLimitEnum, AbstractExecute> executeMap = new HashMap<>();

    /**
     * 可支持的生成器
     */
    protected final List<QuestionGenerator> generators = new ArrayList<>();



    @Override
    public ArithmeticPaper generator(List<FormulaLimitEnum> formulaLimits, QuestionParameterLimit questionParameterLimit) {
        // 初始化支持的所有的生成器
        initMap();
        // 新建试卷
        ArithmeticPaper arithmeticPaper = new ArithmeticPaper();
        List<Arithmetic> arithmetics = new ArrayList<>();
        arithmeticPaper.setArithmetics(arithmetics);
        // 获取一个执行链
        AbstractExecute execute = initExecuteChain(arithmeticPaper,formulaLimits,questionParameterLimit);
        return doGenerator(execute,arithmeticPaper,questionParameterLimit);
    }

    protected abstract ArithmeticPaper doGenerator(AbstractExecute executeChain,ArithmeticPaper arithmeticPaper,QuestionParameterLimit questionParameterLimit);

    /**
     * 初始化Map
     */
    private void initMap(){
        executeMap.clear();
        executeMap.put(FormulaLimitEnum.MUST_DIVISOR,new MustDivisorExecute());
        executeMap.put(FormulaLimitEnum.NON_REPEATABLE,new NonRepeatableExecute());
        executeMap.put(FormulaLimitEnum.QUESTION_RESULT_CHECK,new QuestionResultCheck());
    }

    /**
     * 初始化规定的限制执行链
     * @param arithmeticPaper 试卷
     * @param formulaLimitEnumList   限制列表
     * @param questionParameterLimit 限制参数
     * @return 执行链首部执行器
     */
    private AbstractExecute initExecuteChain(ArithmeticPaper arithmeticPaper,
                                             List<FormulaLimitEnum> formulaLimitEnumList,
                                             QuestionParameterLimit questionParameterLimit){
        // 初始化一个默认的数量限制执行器
        AbstractExecute execute = new NumCheckExecute();
        execute.setArithmeticPaper(arithmeticPaper);
        // 暂存执行链的第一个执行器
        AbstractExecute returnExecute = execute;
        execute.setQuestionParameterLimit(questionParameterLimit);
        // 配置执行链
        for (FormulaLimitEnum formulaLimitEnum : formulaLimitEnumList) {
            if (! FormulaLimitEnum.NUM_LIMIT.equals(formulaLimitEnum)){
                // 根据限制条件获取对应的限制执行链
                AbstractExecute nextExecute = executeMap.get(formulaLimitEnum);
                nextExecute.setArithmeticPaper(arithmeticPaper);
                nextExecute.setQuestionParameterLimit(questionParameterLimit);
                execute.setNextExecute( nextExecute);
                execute = nextExecute;
            }
        }
        return returnExecute;
    }

    /**
     * 将合法的算式放入算式列表中
     * @param arithmeticPaper 试卷
     * @param arithmetic 问题
     */
    protected void putLegalQuestion(ArithmeticPaper arithmeticPaper, Arithmetic arithmetic){
        arithmeticPaper.getArithmetics().add(arithmetic);
    }

}
