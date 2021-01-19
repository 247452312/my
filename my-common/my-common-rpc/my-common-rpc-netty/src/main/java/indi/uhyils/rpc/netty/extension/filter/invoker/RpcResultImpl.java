package indi.uhyils.rpc.netty.extension.filter.invoker;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 11时16分
 */
public class RpcResultImpl implements RpcResult {

    Object data;

    @Override
    public Object get() {
        return data;
    }

    @Override
    public Object set(Object obj) {
        Object result = data;
        data = obj;
        return result;
    }
}
