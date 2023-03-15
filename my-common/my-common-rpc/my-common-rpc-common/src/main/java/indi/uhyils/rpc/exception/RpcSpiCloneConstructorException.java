package indi.uhyils.rpc.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 11时22分
 */
public class RpcSpiCloneConstructorException extends RpcSpiCloneException {


    public RpcSpiCloneConstructorException(Throwable cause, Class<?> targetClass) {
        super("构造器未找到异常:", cause, targetClass);
    }
}
