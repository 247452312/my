package indi.uhyils.rpc.netty.extension.filter.filter;

import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastDefaultConsumerInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastDefaultProviderInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时52分
 */
public class InvokerChainBuilder {

    public static RpcInvoker buildProviderInvokerChain(LastDefaultProviderInvoker lastDefaultInvoker) {
        RpcInvoker last = lastDefaultInvoker;
        List<ProviderRequestFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ProviderRequestFilter.class);

        for (int i = chain.size() - 1; i >= 0; i--) {
            final ProviderRequestFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }

    public static RpcInvoker buildConsumerInvokerChain(LastDefaultConsumerInvoker lastDefaultInvoker) {
        RpcInvoker last = lastDefaultInvoker;
        List<ConsumerResponseFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ConsumerResponseFilter.class);
        for (int i = chain.size() - 1; i >= 0; i--) {
            final ConsumerResponseFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            RpcInvoker rpcInvoker = context -> providerInvoker.invoke(next, context);
            last = rpcInvoker;
        }
        return last;
    }
}
