package com.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LogUtils {

    private LogUtils() {}


    // Lấy logger động theo class gọi
    private static Logger getLogger() {
        // [3] = class gọi phương thức LogUtils
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        return LogManager.getLogger(className);
    }

    // ===== Logging methods =====
    public static void info(String message) {
        getLogger().info(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void error(String message, Throwable t) {
        getLogger().error(message, t);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }

    public static void fatal(String message) {
        getLogger().fatal(message);
    }

    // ===== MDC context helpers =====
    public static void setContext(String key, String value) {
        ThreadContext.put(key, value);
    }

    public static void clearContext() {
        ThreadContext.clearAll();
    }
}
