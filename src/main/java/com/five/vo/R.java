package com.five.vo;

import lombok.Data;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 14:38
 */
@Data
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(200, null, null);
    }

    public static <T> R<T> success(RCodeEnum rCodeEnum) {
        return new R<>(rCodeEnum.getCode(), rCodeEnum.getMsg(), null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(200, null, data);
    }

    public static <T> R<T> success(String msg) {
        return new R<>(200, msg, null);
    }
    public static <T> R<T> success(String msg, T data) {
        return new R<>(200, msg, data);
    }



    public static <T> R<T> fail() {
        return new R<>(500, null, null);
    }

    public static <T> R<T> fail(RCodeEnum rCodeEnum) {
        return new R<>(rCodeEnum.getCode(), rCodeEnum.getMsg(), null);
    }

    public static <T> R<T> fail(Integer code) {
        return new R<>(code, null, null);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(500, msg, null);
    }
}
