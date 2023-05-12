package com.five.service.questions.chain;


import com.five.enums.OperationEnum;
import com.five.service.questions.model.ExecuteResult;
import com.five.service.questions.model.Question;
import com.five.util.ArithmeticUtil;

/**
 * @description 必须整除检查
 */
public class MustDivisorExecute extends AbstractExecute{

    @Override
    public ExecuteResult execute(Question question) {
        // 如果在检查的过程中发现了除数为0的情况也是错的
        // 这里要考虑连除还有乘的情况
        int ans = question.getOperateNums().get(0);
        for (int i = 1;i < question.getOperateNums().size();i++){

            OperationEnum operationEnum = question.getOperateSymbols().get(i - 1);
            Integer operateNum2 = question.getOperateNums().get(i);
            if (OperationEnum.DIVISION.equals(operationEnum)){
                // 如果除数为0则直接返回错误
                if (operateNum2 == 0){
                    return new ExecuteResult();
                }
                // 如果不能被整除则返回错误
                if (ans % operateNum2 != 0){
                    return new ExecuteResult();
                }
                // 更新当前的数值（用于后面的连乘或连除的判断)
                ans = ArithmeticUtil.getOperateResult(ans,operationEnum,operateNum2);
            }

        }
        if (nextExecute != null){
            return nextExecute.execute(question);
        }
        return new ExecuteResult(true,question);
    }

}
