package com.five.util;

/**
 * description:
 *
 * @author fly
 * @since 2022/12/7 21:21
 */
public class UserInfoThreadLocal {

    private static final ThreadLocal<TokenInfo> USER_INFO = new ThreadLocal<>();

    public static void put(TokenInfo tokenInfo) {
        USER_INFO.set(tokenInfo);
    }

    public static TokenInfo get() {
        return USER_INFO.get();
    }

    public static void clear() {
        if (UserInfoThreadLocal.get() != null) {
            USER_INFO.remove();
        }
    }
}
