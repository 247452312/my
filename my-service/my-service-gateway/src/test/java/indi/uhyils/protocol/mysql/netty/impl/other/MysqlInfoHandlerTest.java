package indi.uhyils.protocol.mysql.netty.impl.other;

import indi.uhyils.mysql.decode.impl.MysqlDecoderImpl;
import indi.uhyils.mysql.handler.MysqlInfoHandler;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月23日 14时15分
 */
public class MysqlInfoHandlerTest extends ChannelInboundHandlerAdapter implements MysqlInfoHandler, ChannelInboundHandler {


    private MysqlNettyClientTest mysqlNettyClientTest;

    /**
     * 连接
     */
    private Channel mysqlChannel;


    public MysqlInfoHandlerTest() {
    }

    /**
     * 初始化连接
     *
     * @param ctx
     *
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.mysqlChannel = ctx.channel();
        mysqlNettyClientTest = new MysqlNettyClientTest(this);
        //入口连接
        InetSocketAddress inetSocketAddress = (InetSocketAddress) mysqlChannel.localAddress();
        LogUtil.info("mysql 连接!" + inetSocketAddress);

        mysqlNettyClientTest.channelActive();
    }

    /**
     * 接收到信息时调用
     *
     * @param ctx
     * @param msg
     *
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        /**
         * 因{@link MysqlDecoderImpl#decode} 所以这里一定为byte[]
         */
        byte[] requestMysqlBytes = (byte[]) msg;
        mysqlNettyClientTest.send(requestMysqlBytes);
    }

    /**
     * 发送数据
     *
     * @param msg
     */
    public void send(byte[] msg) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg);
        this.mysqlChannel.writeAndFlush(buf);
    }


}
