package indi.uhyils.rpc.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月30日 18时40分
 */
public class MyRpcGreaterThanOneException extends RpcException {
    public MyRpcGreaterThanOneException() {
        super("MyRpc注解不能多于一个");
    }
}
