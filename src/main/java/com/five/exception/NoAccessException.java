package com.five.exception;

import com.five.vo.RCodeEnum;

public class NoAccessException extends BaseException{

    public NoAccessException(){
        super();
    }

    public NoAccessException(RCodeEnum responseDataEnum){
        super(responseDataEnum);
    }

    public NoAccessException(String msg){
        super(msg);
    }

}
