package indi.uhyils.exception;

/**
 * 共用业务异常
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 16时47分
 */
public class MyBusinessException extends MyException {

    public MyBusinessException() {
        super();
    }

    public MyBusinessException(String message) {
        super(message);
    }

    public MyBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBusinessException(Throwable cause) {
        super(cause);
    }

    protected MyBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
