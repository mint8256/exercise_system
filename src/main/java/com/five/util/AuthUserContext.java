package com.five.util;

public class AuthUserContext {

    private static final ThreadLocal<TokenInfo> USER_INFO = new ThreadLocal<>();

    public static void put(TokenInfo tokenInfo) {
        USER_INFO.set(tokenInfo);
    }

    public static TokenInfo get() {
        return USER_INFO.get();
    }

    public static void clear() {
        if (AuthUserContext.get() != null) {
            USER_INFO.remove();
        }
    }
}
