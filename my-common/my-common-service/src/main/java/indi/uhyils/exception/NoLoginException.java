package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 16时48分
 */
public class NoLoginException extends MyBusinessException {

    public NoLoginException() {
        super("未登录,请去登录");
    }
}
