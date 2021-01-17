package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.consumer.RpcNettyNormalConsumer;
import indi.uhyils.rpc.netty.pojo.RpcData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class DubboResponseInHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private RpcCallBack callBack;
    /**
     * 观察者模式
     */
    private RpcNettyNormalConsumer netty;


    public DubboResponseInHandler(RpcCallBack callBack, RpcNettyNormalConsumer netty) {
        this.callBack = callBack;
        this.netty = netty;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        RpcData rpcData = callBack.getRpcData(bytes);
        netty.put(rpcData);
    }
}