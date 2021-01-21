package indi.uhyils.rpc.netty.extension.filter.filter;

import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastConsumerRequestInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastConsumerResponseInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastProviderInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时52分
 */
public class InvokerChainBuilder {

    public static RpcInvoker buildProviderAroundInvokerChain(LastProviderInvoker lastDefaultInvoker) {
        RpcInvoker last = lastDefaultInvoker;
        List<ProviderFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ProviderFilter.class);

        for (int i = chain.size() - 1; i >= 0; i--) {
            final ProviderFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }

    public static RpcInvoker buildConsumerResponseInvokerChain(LastConsumerResponseInvoker lastDefaultInvoker) {
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

    public static RpcInvoker buildConsumerSendInvokerChain(LastConsumerRequestInvoker lastConsumerRequestInvoker) {
        RpcInvoker last = lastConsumerRequestInvoker;
        List<ConsumerRequestFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ConsumerRequestFilter.class);
        for (int i = chain.size() - 1; i >= 0; i--) {
            final ConsumerRequestFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            RpcInvoker rpcInvoker = context -> providerInvoker.invoke(next, context);
            last = rpcInvoker;
        }
        return last;
    }
}
