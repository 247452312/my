package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月21日 09时09分
 */
public class ServiceResultException extends MyBusinessException {

    /**
     * 异常code
     */
    private final Integer errorCode;

    /**
     * 异常信息
     */
    private final String msg;

    public ServiceResultException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}
