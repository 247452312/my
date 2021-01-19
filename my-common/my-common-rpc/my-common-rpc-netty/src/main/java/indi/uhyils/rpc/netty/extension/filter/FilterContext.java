package indi.uhyils.rpc.netty.extension.filter;

import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * invoker时传递的上下文
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时21分
 */
public class FilterContext {

    private RpcResult rpcResult;

    private Map<String, Object> info = new HashMap<>();

    public Object put(String name, Object info) {
        return this.info.put(name, info);
    }

    public Object remove(String name) {
        return this.info.remove(name);
    }

    public Object get(String name) {
        return this.info.get(name);
    }

    public Set<Map.Entry<String, Object>> entitySet() {
        return info.entrySet();
    }

    public RpcResult getRpcResult() {
        return rpcResult;
    }

    public void setRpcResult(RpcResult rpcResult) {
        this.rpcResult = rpcResult;
    }
}
