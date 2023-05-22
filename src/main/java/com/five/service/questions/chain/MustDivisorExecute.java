package com.five.service.questions.chain;


import com.five.enums.OperationEnum;
import com.five.service.questions.model.Arithmetic;
import com.five.service.questions.model.ExecuteResult;
import com.five.util.ArithmeticUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 必须整除检查
 */
public class MustDivisorExecute extends AbstractExecute {

    @Override
    public ExecuteResult execute(Arithmetic arithmetic) {
        if (!isLegal(arithmetic)){
            return new ExecuteResult();
        }
        if (nextExecute != null) {
            return nextExecute.execute(arithmetic);
        }
        return new ExecuteResult(true, arithmetic);
    }

    /**
     * 判断一个算法是否含有不能整除的
     * @param arithmetic 算式
     * @return 是否合法
     */
    private boolean isLegal(Arithmetic arithmetic){
        // 判断方法更新：先找出所有/所在的位置
        List<Integer> divisionIndices = new ArrayList<>();
        for (int i = 0; i < arithmetic.getOperateSymbols().size(); i++) {
            if (equalsDivision(arithmetic.getOperateSymbols().get(i))) {
                divisionIndices.add(i);
            }
        }
        // 计算所有除号的地方
        for (Integer divisionIndex : divisionIndices) {
            // 现在是除号，那么前面的乘除都要计算一遍
            int i = divisionIndex;
            // 先寻找整个连乘/连除的开头
            for (; i >= 0; i--) {
                if (!equalsDivision(arithmetic.getOperateSymbols().get(i)) &&
                        !equalsMultiply(arithmetic.getOperateSymbols().get(i))) {
                    break;
                }
            }
            int operationNum1 = arithmetic.getOperateNums().get(++i);
            // 然后挨个计算
            for (int j = i; j < divisionIndex; j++) {
                operationNum1 = ArithmeticUtil.getOperateResult(operationNum1, arithmetic.getOperateSymbols().get(j), arithmetic.getOperateNums().get(j + 1));
            }
            int operationNum2 = arithmetic.getOperateNums().get(divisionIndex + 1);
            if (operationNum2 == 0 || (operationNum1 % operationNum2 != 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个操作符是否是除号
     *
     * @param operationEnum 操作符
     * @return 是否是除号
     */
    private boolean equalsDivision(OperationEnum operationEnum) {
        return OperationEnum.DIVISION.equals(operationEnum);
    }

    /**
     * 判断一个操作符是否是乘号
     *
     * @param operationEnum 操作符
     * @return 是否是乘号
     */
    private boolean equalsMultiply(OperationEnum operationEnum) {
        return OperationEnum.MULTIPLY.equals(operationEnum);
    }

}

