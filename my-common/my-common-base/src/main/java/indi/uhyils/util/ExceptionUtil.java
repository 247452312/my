package indi.uhyils.util;

import java.io.PrintWriter;
import java.io.StringWriter;

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
    public static String parseException(Throwable throwable) {
        String exceptionStr = null;
        if (throwable != null) {
            StringWriter out = new StringWriter();
            throwable.printStackTrace(new PrintWriter(out, true));
            exceptionStr = out.toString();
        }
        return exceptionStr;
    }

}
