package com.five.exception;

import com.five.vo.R;
import com.five.vo.RCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})
    public R sQLIntegrityConstraintViolationException(Exception e) {
        log.error(e.getMessage());
        return R.fail(RCodeEnum.FOREIGN_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public R baseException(BaseException e) {
        RCodeEnum codeEnum = e.getResponseDataEnum();
        return R.fail(codeEnum);
    }


    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        LOGGER.error("================系统发生了错误==========");
        LOGGER.error("发生错误的原因：{}", e.getMessage());
        LOGGER.error("异常链为   ", e.getCause());
        e.printStackTrace();
        return R.fail(RCodeEnum.FAIL);
    }
}
