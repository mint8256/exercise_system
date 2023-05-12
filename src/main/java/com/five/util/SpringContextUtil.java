package com.five.util;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author fly
 * @since 2023/3/3 11:10
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 加载Spring上下文对象，用来静态获取bean
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {

        log.info("--------------- 此处加载了上下文对象 ------------------------------------------- ");
        System.out.println(applicationContext);
        SpringContextUtil.applicationContext = applicationContext;

    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据beanName获取bean
     *
     * @param name beanName
     * @return bean对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return getApplicationContext().getBean(name, requiredType);
    }

    /**
     * 根据类型获取bean
     *
     * @param beanType bean类型
     * @param <T>      需要被spring托管
     * @return 获取到的bean
     */
    public static <T> T getBean(Class<T> beanType) {
        return getApplicationContext().getBean(beanType);
    }

    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return getApplicationContext().isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return getApplicationContext().getType(name);
    }

    /**
     * 检查applicationContext是否存在
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("未检测到bean，not found SpringContextUtil");
        }
    }

}
