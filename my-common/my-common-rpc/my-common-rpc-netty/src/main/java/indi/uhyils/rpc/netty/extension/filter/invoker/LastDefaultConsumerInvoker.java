package indi.uhyils.rpc.netty.extension.filter.invoker;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoader;
import indi.uhyils.rpc.netty.extension.RpcExtensionLoaderTypeEnum;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.step.template.ConsumerResponseByteExtension;
import indi.uhyils.rpc.netty.extension.step.template.ConsumerResponseDataExtension;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时27分
 */
public class LastDefaultConsumerInvoker implements RpcInvoker {

    /**
     * 回调
     */
    private final RpcCallBack callback;

    /**
     * 消费者接收回复byte拦截器
     */
    private List<ConsumerResponseByteExtension> consumerResponseByteFilters;
    /**
     * 消费者接收回复data拦截器
     */
    private List<ConsumerResponseDataExtension> consumerResponseDataFilters;

    private ChannelHandlerContext ctx;
    private ByteBuf msg;


    public LastDefaultConsumerInvoker(RpcCallBack callback, ChannelHandlerContext ctx, ByteBuf msg) {
        this.callback = callback;
        this.ctx = ctx;
        this.msg = msg;
        consumerResponseByteFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ConsumerResponseByteExtension.class);
        consumerResponseDataFilters = RpcExtensionLoader.getExtensionByClass(RpcExtensionLoaderTypeEnum.RPC_STEP, ConsumerResponseDataExtension.class);
    }

    @Override
    public RpcResult invoke(FilterContext context) throws RpcException, ClassNotFoundException {
        RpcResult rpcResult = context.getRpcResult();
        if (rpcResult == null) {
            context.setRpcResult(new RpcResultImpl());
        }

        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        // ConsumerResponseByteFilter
        for (ConsumerResponseByteExtension filter : consumerResponseByteFilters) {
            bytes = filter.doFilter(bytes);
        }
        RpcData rpcData = callback.getRpcData(bytes);
        // ConsumerResponseDataFilter
        for (ConsumerResponseDataExtension filter : consumerResponseDataFilters) {
            rpcData = filter.doFilter(rpcData);
        }
        rpcResult = context.getRpcResult();
        rpcResult.set(rpcData);
        return rpcResult;
    }

}
