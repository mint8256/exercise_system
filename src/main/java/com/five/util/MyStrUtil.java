package com.five.util;

import cn.hutool.core.util.StrUtil;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 19:47
 */
public class MyStrUtil {

    /**
     * 将两个字符串trim后再进行比较
     */
    public static boolean equal(String a, String b) {
        return StrUtil.trim(a).equals(StrUtil.trim(b));
    }

}
