package indi.uhyils.rpc.exception;

/**
 * rpc对方报错
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月27日 09时29分
 */
public class MyRpcProviderThrowException extends RpcException {


    public MyRpcProviderThrowException(String message) {
        super(message);
    }

    public MyRpcProviderThrowException(Throwable th) {
        super(th);
    }

}
