package com.five.service.questions.paper;

import com.five.enums.FormulaLimitEnum;
import com.five.service.questions.model.ArithmeticPaper;
import com.five.service.questions.model.QuestionParameterLimit;

import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/12 20:34
 */
public interface PaperGenerator {

    ArithmeticPaper generator(List<FormulaLimitEnum> formulaLimits, QuestionParameterLimit questionParameterLimit);

}
