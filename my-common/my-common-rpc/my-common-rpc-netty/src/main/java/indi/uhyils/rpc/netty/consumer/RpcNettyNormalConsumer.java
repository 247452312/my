package indi.uhyils.rpc.netty.consumer;

import indi.uhyils.rpc.netty.AbstractRpcNetty;
import indi.uhyils.rpc.netty.handler.RpcPrintInHandler;
import indi.uhyils.rpc.netty.handler.RpcPrintOutHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 15时06分
 */
public class RpcNettyNormalConsumer extends AbstractRpcNetty {

    /**
     * 客户端
     */
    private EventLoopGroup group;

    /**
     * 客户端channel
     */
    private ChannelFuture channelFuture;

    @Override
    public Boolean init(String host, Integer port) {
        Bootstrap client = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        this.group = group;
        setBootstrap(client);
        client.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new RpcPrintInHandler());
                        p.addLast(new RpcPrintOutHandler());
                    }
                });

        //连接服务器
        this.channelFuture = client.connect(host, port);
        return true;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public void setGroup(EventLoopGroup group) {
        this.group = group;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    @Override
    public Boolean shutdown() {
        try {
            if (group != null) {
                this.group.shutdownGracefully();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean sendMsg(byte[] bytes) {
        ChannelFuture channelFuture = getChannelFuture();
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(bytes);
        channelFuture.channel().writeAndFlush(buf);
        return true;
    }
}
