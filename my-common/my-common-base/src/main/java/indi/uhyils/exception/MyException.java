package indi.uhyils.exception;

/**
 * 共用异常
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 16时47分
 */
public class MyException extends RuntimeException {

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    protected MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
