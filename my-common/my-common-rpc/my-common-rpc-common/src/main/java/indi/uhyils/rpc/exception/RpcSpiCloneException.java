package indi.uhyils.rpc.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 11时20分
 */
public class RpcSpiCloneException extends RpcException {

    private final Class<?> targetClass;

    public RpcSpiCloneException(Throwable cause, Class<?> targetClass) {
        super(cause);
        this.targetClass = targetClass;
    }

    public RpcSpiCloneException(String message, Throwable cause, Class<?> targetClass) {
        super(message, cause);
        this.targetClass = targetClass;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
}
