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
        List<ProviderFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ProviderFilter.class);

        for (int i = chain.size() - 1; i >= 0; i--) {
            final ProviderFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }

    public static RpcInvoker buildConsumerInvokerChain(LastDefaultConsumerInvoker lastDefaultInvoker) {
        RpcInvoker last = lastDefaultInvoker;
        List<ConsumerFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ConsumerFilter.class);
        for (int i = chain.size() - 1; i >= 0; i--) {
            final ConsumerFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }
}
