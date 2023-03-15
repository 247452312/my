package indi.uhyils.rpc.exception;

/**
 * rpc创建某个类时出现错误
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 10时26分
 */
public class RpcSpiInitException extends RpcException {

    /**
     * 要初始化的类
     */
    private final Class<?> initClass;

    public RpcSpiInitException(Throwable cause, Class<?> initClass) {
        super(cause);
        this.initClass = initClass;
    }

    public Class<?> getInitClass() {
        return initClass;
    }
}
