package indi.uhyils.rpc.netty.extension.filter.filter;

import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastConsumerInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastProviderInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时52分
 */
public class InvokerChainBuilder {

    private InvokerChainBuilder() {
    }

    public static RpcInvoker buildProviderAroundInvokerChain(LastProviderInvoker lastDefaultInvoker) {
        // 最后一个连接器,系统类,必须执行
        RpcInvoker last = lastDefaultInvoker;
        // 获取已经排序好的所有的拦截器
        List<ProviderFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ProviderFilter.class);

        for (int i = chain.size() - 1; i >= 0; i--) {
            final ProviderFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }

    public static RpcInvoker buildConsumerSendInvokerChain(LastConsumerInvoker lastConsumerInvoker) {
        RpcInvoker last = lastConsumerInvoker;
        List<ConsumerFilter> chain = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_FILTER, ConsumerFilter.class);
        for (int i = chain.size() - 1; i >= 0; i--) {
            final ConsumerFilter providerInvoker = chain.get(i);
            final RpcInvoker next = last;
            last = context -> providerInvoker.invoke(next, context);
        }
        return last;
    }
}
