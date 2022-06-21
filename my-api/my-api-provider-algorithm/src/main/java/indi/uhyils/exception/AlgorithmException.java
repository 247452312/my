package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月08日 09时09分
 */
public class AlgorithmException extends RuntimeException {

    public AlgorithmException() {
    }

    public AlgorithmException(String message) {
        super(message);
    }

    public AlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmException(Throwable cause) {
        super(cause);
    }

    public AlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
