package com.five.exception;

import com.five.vo.RCodeEnum;

public class BaseException extends RuntimeException {

    protected RCodeEnum rCodeEnum;

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(RCodeEnum responseDataEnum) {
        super(responseDataEnum.getMsg());
        this.rCodeEnum = responseDataEnum;
    }

    public RCodeEnum getResponseDataEnum() {
        return this.rCodeEnum;
    }

}
