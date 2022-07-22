package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 16时49分
 */
public class LoginOutException extends MyBusinessException {

    public LoginOutException() {
        super("登录已过期,请重新登录");
    }
}
