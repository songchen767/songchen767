package com.aia.util;

/**
 * @author chentao
 * @version 创建时间: 2023-11-24 11:29
 */
public class TokenUtil {

    private static ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void clearToken() {
        tokenThreadLocal.remove();
    }
}
