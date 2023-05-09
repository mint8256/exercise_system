package com.five.util;


import com.five.exception.ParamException;
import com.five.vo.RCodeEnum;

public class ParamAssert {
    public static void notNull(Object obj){
        notNull(obj, RCodeEnum.PARAM_WRONG);
    }

    public static void notNull(Object object, RCodeEnum rCodeEnum){
        if (object == null){
            throw new ParamException(rCodeEnum);
        }
    }

    public static void notNull(Object obj,String msg){
        if (obj == null){
            throw new ParamException(msg);
        }
    }

    public static void isNull(Object obj){
        isNull(obj,RCodeEnum.PARAM_WRONG);
    }

    public static void isNull(Object obj,String msg){
        if (obj != null){
            throw new ParamException(msg);
        }
    }

    public static void isNull(Object obj,RCodeEnum rCodeEnum){
        if (obj != null){
            throw new ParamException(rCodeEnum);
        }
    }

    public static void notEquals(Object one,Object two,RCodeEnum rCodeEnum){
        ParamAssert.notNull(one);
        ParamAssert.notNull(two);
        if (!one.equals(two)){
            throw new ParamException(rCodeEnum);
        }
    }


    public static void isTrue(Boolean result,String msg){
        ParamAssert.notNull(result);
        if (!result){
            throw new ParamException(msg);
        }
    }

    public static void isTrue(Boolean result,RCodeEnum rCodeEnum){
        ParamAssert.notNull(result);
        if (!result){
            throw new ParamException(rCodeEnum);
        }
    }
}
