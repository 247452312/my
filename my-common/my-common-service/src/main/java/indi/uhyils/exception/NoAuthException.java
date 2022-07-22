package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 16时52分
 */
public class NoAuthException extends MyBusinessException {

    public NoAuthException() {
        super("您没有此服务的权限,请联系管理员!");
    }
}
