package indi.uhyils.rpc.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 16时06分
 */
public class RpcNetException extends RpcException {

    public RpcNetException() {
        super("网络异常错误");
    }

    public RpcNetException(String message) {
        super("网络异常错误:" + message);
    }
}
