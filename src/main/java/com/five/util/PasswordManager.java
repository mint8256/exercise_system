package com.five.util;

import cn.hutool.crypto.SecureUtil;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 18:58
 */
public class PasswordManager {


    /**
     * 校验输入的字符串是否和真正的密码相同
     *
     * @param input 输入的密码
     * @param real  db中的密码
     * @return 是否相同
     */
    public static boolean checkPass(String input, String real) {
        return real.equals(encryption(input));
    }

    /**
     * 对字符串进行SHA256加密
     *
     * @param s 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryption(String s) {
        return SecureUtil.sha256(s);
    }

}
