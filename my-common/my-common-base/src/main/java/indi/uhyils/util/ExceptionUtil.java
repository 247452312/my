package indi.uhyils.util;

import java.util.Arrays;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月11日 08时16分
 */
public final class ExceptionUtil {

    private ExceptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 异常解析
     *
     * @param throwable
     *
     * @return
     */
    public static String toString(Throwable throwable) {
        String message = throwable.getMessage();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        return message + Arrays.toString(stackTrace);
    }

}
