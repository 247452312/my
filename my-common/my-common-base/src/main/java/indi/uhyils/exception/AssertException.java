package indi.uhyils.exception;

/**
 * rpc断言错误
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月15日 21时56分
 */
public class AssertException extends MyBusinessException {

    public AssertException(String message) {
        super(message);
    }
}
