package com.five.util;


import com.five.enums.OperationEnum;

/**
 * @description 运算工具类（扩展可以增加逆波兰进行计算）
 */
public class ArithmeticUtil {

    /**
     * 获取两个操作数和符号的运算结果
     * @param operateNum1 操作数1
     * @param operationEnum 运算符号
     * @param operateNum2 操作数2
     * @return 运算结果
     */
    public static int getOperateResult(int operateNum1, OperationEnum operationEnum, int operateNum2){
        try {
            if (OperationEnum.ADD.equals(operationEnum)){
                return operateNum1 + operateNum2;
            } else if (OperationEnum.SUB.equals(operationEnum)) {
                return operateNum1 - operateNum2;
            }else if (OperationEnum.MULTIPLY.equals(operationEnum)) {
                return operateNum1 * operateNum2;
            }else if (OperationEnum.DIVISION.equals(operationEnum)) {
                return operateNum1 / operateNum2;
            }
        }catch (Exception ignored){}
        return -1;
    }

}
