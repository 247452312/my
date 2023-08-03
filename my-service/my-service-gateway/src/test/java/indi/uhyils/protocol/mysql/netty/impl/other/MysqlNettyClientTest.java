package indi.uhyils.protocol.mysql.netty.impl.other;

import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.util.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月23日 14时14分
 */
public class MysqlNettyClientTest {

    private final MysqlInfoHandlerTest mysqlInfoHandlerTest;

    private EventLoopGroup group;

    private Bootstrap bootstrap;

    private ChannelFuture connect;

    public MysqlNettyClientTest(MysqlInfoHandlerTest mysqlInfoHandlerTest) {
        this.mysqlInfoHandlerTest = mysqlInfoHandlerTest;
    }


    /**
     * 初始化连接
     *
     * @return
     */
    public void channelActive() {
        LogUtil.info("-------------连接初始化!!!!!!!!!!!");
        String host = "prod";
        Integer port = 3306;

        Bootstrap client = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        this.group = eventLoopGroup;
        this.bootstrap = client;
        client.group(eventLoopGroup)
              .channel(NioSocketChannel.class)
              .handler(new LoggingHandler(LogLevel.DEBUG))
              .handler(new ChannelInitializer<NioSocketChannel>() {

                  @Override
                  protected void initChannel(NioSocketChannel ch) {
                      ChannelPipeline p = ch.pipeline();
                      p.addLast("aaa", new TestHandler());
                  }
              });

        //连接服务器
        this.connect = client.connect(host, port);
    }

    /**
     * 发送信息
     *
     * @param requestMysqlBytes
     *
     * @return
     */
    public void send(byte[] requestMysqlBytes) {
        LogUtil.info("client向mysql发送信息:");
        LogUtil.info("\n" + MysqlUtil.dump(requestMysqlBytes));
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(requestMysqlBytes);
        connect.channel().writeAndFlush(buf);
    }


    private class TestHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

            byte[] bytes = new byte[msg.readableBytes()];
            msg.readBytes(bytes);
            LogUtil.info("mysql向client发送信息:");
            LogUtil.info("\n" + MysqlUtil.dump(bytes));
            mysqlInfoHandlerTest.send(bytes);
        }
    }

}
