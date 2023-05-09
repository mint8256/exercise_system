package com.five.aop.annotation;


import com.five.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthVerify {
    /**
     * 角色的权限枚举：默认是用户操作
     */
    RoleEnum[] roles() default {RoleEnum.STUDENT};
}
