package indi.uhyils.rpc.netty.extension.filter.invoker;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 21时07分
 */
public class LastConsumerRequestInvoker implements RpcInvoker {

    /**
     * 数据
     */
    private byte[] data;

    /**
     * channel
     */
    private ChannelFuture channelFuture;

    public LastConsumerRequestInvoker(byte[] data, ChannelFuture channelFuture) {
        this.data = data;
        this.channelFuture = channelFuture;
    }

    @Override
    public RpcResult invoke(FilterContext context) throws RpcException, ClassNotFoundException {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(data);
        channelFuture.channel().writeAndFlush(buf);
        return context.getRpcResult();
    }
}
