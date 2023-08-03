package indi.uhyils.util;

import indi.uhyils.exception.AssertException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月15日 22时06分
 */
public class Asserts {

    /**
     * 断言正常并且返回异常
     *
     * @return
     */
    public static Optional<AssertException> assertTrueAndGetException(boolean condition, String msg, Object... params) {
        AssertException assertException = assertTrueAndGetException(condition, 3, msg, params);
        return Optional.ofNullable(assertException);
    }

    /**
     * 断言正常并且返回异常
     *
     * @return
     */
    public static AssertException makeException(String msg, Object... params) {
        return assertTrueAndGetException(false, 3, msg, params);
    }

    /**
     * 断言正常并且返回异常
     *
     * @return
     */
    public static AssertException throwOptionalException() {
        return assertTrueAndGetException(false, 4, "Optional异常");
    }

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
    public static void throwException(String msg, Object... params) {
        msg = MessageFormatter.arrayFormat(msg, params).getMessage();
        assertTrue(false, 3, msg);
    }

    /**
     * 断言错误
     */
    public static void throwException(Exception e) {
        if (e instanceof AssertException) {
            throw (AssertException) e;
        }
        assertTrue(false, 3, e.getMessage());
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
     * 断言相等
     *
     * @param firstObj  第一个实例
     * @param secondObj 第二个实例
     * @param msg       不相等时消息
     * @param params    消息中的参数
     */
    public static void assertEqual(Object firstObj, Object secondObj, String msg, String... params) {
        assertTrue(Objects.equals(firstObj, secondObj), 3, msg, params);
    }

    /**
     * 断言相等
     *
     * @param firstObj  第一个实例
     * @param secondObj 第二个实例
     */
    public static void assertEqual(Object firstObj, Object secondObj) {
        assertTrue(Objects.equals(firstObj, secondObj), 3, "断言错误");
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
     * 断言异常
     *
     * @param runnable
     */
    public static void assertException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            LogUtil.info("成功报错");
            LogUtil.error(e);
            return;
        }
        AssertException assertException = new AssertException("没有异常的异常!");
        removeExceptionTrace(assertException, 2);
        LogUtil.error(assertException);
        throw assertException;
    }

    /**
     * 断言无异常
     *
     * @param runnable
     */
    public static void assertNoException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            LogUtil.error(e);
            throw e;
        }

    }

    /**
     * 断言指定类不为null
     *
     * @param obj
     * @param <T> 任意类型
     *
     * @return
     */
    public static <T> T assertNotNull(T obj) {
        assertTrue(obj != null, 4, "指定实例为空");
        return obj;
    }

    /**
     * 断言指定类不为null
     *
     * @param str
     *
     * @return
     */
    public static String assertNotNull(String str) {
        assertTrue(StringUtil.isNotEmpty(str), 4, "指定字符串为空");
        return str;
    }

    /**
     * 断言执行容器不为空
     *
     * @param collection 容器
     * @param <T>
     *
     * @return
     */
    public static <T extends Collection<?>> T assertNotEmpty(T collection) {
        assertTrue(CollectionUtil.isNotEmpty(collection), 4, "指定容器为空");
        return collection;
    }

    /**
     * 断言执行容器不为空
     *
     * @param collection 容器
     * @param <T>
     *
     * @return
     */
    public static <T extends Map<?, ?>> T assertNotEmpty(T collection) {
        assertTrue(MapUtil.isNotEmpty(collection), 4, "指定容器为空");
        return collection;
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
            AssertException assertException = new AssertException("断言异常: " + msg);
            removeExceptionTrace(assertException, removeLayerCount);
            LogUtil.error(assertException);
            throw assertException;
        }
    }

    /**
     * 断言正确
     *
     * @param condition        验证
     * @param removeLayerCount 要删除的顶层堆栈的层数
     * @param msg
     */
    private static AssertException assertTrueAndGetException(boolean condition, int removeLayerCount, String msg, Object... params) {
        if (!condition) {
            msg = MessageFormatter.arrayFormat(msg, params).getMessage();
            AssertException assertException = new AssertException("断言异常: " + msg);
            removeExceptionTrace(assertException, removeLayerCount);
            return assertException;
        }
        return null;
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
            AssertException AssertException = new AssertException("断言异常: " + msg);
            removeExceptionTrace(AssertException, removeLayerCount);
            throw AssertException;
        }
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
