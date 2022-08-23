package indi.uhyils.protocol.mysql.netty.impl.other;

import indi.uhyils.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月23日 14时12分
 */
public class MysqlNettyTest extends ChannelInitializer<SocketChannel> {


    private final Integer port = 3306;

    /**
     * 主线程,单线程
     */
    private EventLoopGroup bossGroup;

    /**
     * 工作线程,多线程
     */
    private EventLoopGroup workerGroup;


    public void init() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        LogUtil.info("mysql端口开启,端口号:{}", port.toString());
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
         .option(ChannelOption.SO_BACKLOG, 1024).childHandler(this);
        ChannelFuture f = b.bind(port).sync();

        f.channel().closeFuture();

    }


    @Override
    protected void initChannel(SocketChannel ch) {
        // 由decoder解析 再交由handler处理
        ch.pipeline().addLast(new MysqlTestDecoder(), new MysqlInfoHandlerTest());
    }

}
