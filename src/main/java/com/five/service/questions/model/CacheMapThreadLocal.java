package com.five.service.questions.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2023/5/22 20:03
 */
public class CacheMapThreadLocal {

    public static final ThreadLocal<Map<String,Boolean>> CACHE_MAP = new ThreadLocal<>();

    public static void put(){
        CACHE_MAP.set(new HashMap<>());
    }

    public static Map<String,Boolean> get(){
        return CACHE_MAP.get();
    }

    public static void clear(){
        CACHE_MAP.remove();
    }

}
