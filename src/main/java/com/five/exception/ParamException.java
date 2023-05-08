package com.five.exception;

import com.five.vo.RCodeEnum;

public class ParamException extends BaseException{

    public ParamException(){
        super();
    }

    public ParamException(RCodeEnum responseDataEnum){
        super(responseDataEnum);
    }

    public ParamException(String msg){
        super(msg);
    }

}
