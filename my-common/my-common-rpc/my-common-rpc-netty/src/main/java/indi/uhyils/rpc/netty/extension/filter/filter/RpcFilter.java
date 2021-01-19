package indi.uhyils.rpc.netty.extension.filter.filter;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.extension.RpcExtension;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;

/**
 * handler中执行类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时01分
 */
public interface RpcFilter extends RpcExtension {


    /**
     * 执行netty传输过来的逻辑
     *
     * @param invoker
     * @param invokerContext
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    RpcResult invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException;
}
