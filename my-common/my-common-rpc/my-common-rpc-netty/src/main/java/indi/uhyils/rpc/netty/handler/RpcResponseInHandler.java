package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.RpcExtensionLoader;
import indi.uhyils.rpc.filter.base.RpcFilter;
import indi.uhyils.rpc.filter.template.ConsumerResponseByteFilter;
import indi.uhyils.rpc.filter.template.ConsumerResponseDataFilter;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.consumer.RpcNettyNormalConsumer;
import indi.uhyils.rpc.pojo.RpcData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class RpcResponseInHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private RpcCallBack callBack;
    /**
     * 观察者模式
     */
    private RpcNettyNormalConsumer netty;

    /**
     * 消费者接收回复byte拦截器
     */
    private LinkedList<ConsumerResponseByteFilter> consumerResponseByteFilters = new LinkedList<>();
    /**
     * 消费者接收回复data拦截器
     */
    private LinkedList<ConsumerResponseDataFilter> consumerResponseDataFilters = new LinkedList<>();


    public RpcResponseInHandler(RpcCallBack callBack, RpcNettyNormalConsumer netty) {
        this.callBack = callBack;
        this.netty = netty;

        List extensionByClass = RpcExtensionLoader.getExtensionByClass(RpcFilter.class);
        for (Object byClass : extensionByClass) {
            if (byClass instanceof ConsumerResponseByteFilter) {
                consumerResponseByteFilters.add((ConsumerResponseByteFilter) byClass);
            }
            if (byClass instanceof ConsumerResponseDataFilter) {
                consumerResponseDataFilters.add((ConsumerResponseDataFilter) byClass);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        // ConsumerResponseByteFilter
        for (ConsumerResponseByteFilter filter : consumerResponseByteFilters) {
            bytes = filter.doFilter(bytes);
        }
        RpcData rpcData = callBack.getRpcData(bytes);
        // ConsumerResponseDataFilter
        for (ConsumerResponseDataFilter filter : consumerResponseDataFilters) {
            rpcData = filter.doFilter(rpcData);
        }
        netty.put(rpcData);
    }
}
