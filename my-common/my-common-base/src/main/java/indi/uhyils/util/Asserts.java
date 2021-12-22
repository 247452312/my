package indi.uhyils.util;

import indi.uhyils.exception.AssertException;
import java.util.function.Supplier;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月15日 22时06分
 */
public class Asserts {

    /**
     * 断言正确
     *
     * @param condition 验证
     * @param msg
     */
    public static void assertTrue(boolean condition, String msg) {
        assertTrue(condition, 3, msg);
    }

    /**
     * 断言错误
     *
     * @param msg
     */
    public static void assertException(String msg) {
        assertTrue(false, 3, msg);
    }

    /**
     * 断言正确
     *
     * @param condition 验证
     * @param msg
     */
    public static void assertTrue(boolean condition, String msg, Object... params) {
        assertTrue(condition, 3, msg, params);
    }

    /**
     * 断言正确
     *
     * @param condition 验证
     */
    public static void assertTrue(boolean condition) {
        assertTrue(condition, 3, "断言错误");
    }

    /**
     * 断言正确
     *
     * @param condition        验证
     * @param removeLayerCount 要删除的顶层堆栈的层数
     * @param msg
     */
    private static void assertTrue(boolean condition, int removeLayerCount, String msg, Object... params) {
        if (!condition) {
            msg = MessageFormatter.arrayFormat(msg, params).getMessage();
            AssertException assertException = new AssertException("throw exception " + msg);
            removeExceptionTrace(assertException, removeLayerCount);
            LogUtil.error(assertException);
            throw assertException;
        }
    }


    /**
     * 断言正确
     *
     * @param condition
     * @param msgFunction
     */
    public static void assertTrue(boolean condition, Supplier<String> msgFunction) {
        assertTrue(condition, 3, msgFunction);
    }

    /**
     * 断言正确
     *
     * @param condition
     * @param removeLayerCount
     * @param msgFunction
     */
    private static void assertTrue(boolean condition, int removeLayerCount, Supplier<String> msgFunction) {
        if (!condition) {
            String msg = msgFunction.get();
            AssertException AssertException = new AssertException("throw exception: " + msg);
            removeExceptionTrace(AssertException, removeLayerCount);
            throw AssertException;
        }
    }

    /**
     * 断言异常
     *
     * @param runnable
     */
    public static void assertException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            return;
        }
        AssertException assertException = new AssertException("没有异常的异常!");
        removeExceptionTrace(assertException, 2);
        LogUtil.error(assertException);
        throw assertException;
    }


    /**
     * 删除异常的顶层堆栈信息
     *
     * @param AssertException  异常
     * @param removeLayerCount 要删除异常的顶层堆栈信息数量
     */
    private static void removeExceptionTrace(AssertException AssertException, int removeLayerCount) {
        StackTraceElement[] stackTrace = AssertException.getStackTrace();
        int newCount = stackTrace.length - removeLayerCount;
        StackTraceElement[] newStackTrace = new StackTraceElement[newCount];
        System.arraycopy(stackTrace, removeLayerCount - 1, newStackTrace, 0, newCount);
        AssertException.setStackTrace(newStackTrace);
    }
}
