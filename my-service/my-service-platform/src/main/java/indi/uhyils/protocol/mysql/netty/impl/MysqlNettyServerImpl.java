package indi.uhyils.protocol.mysql.netty.impl;

import indi.uhyils.protocol.mysql.decoder.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.handler.impl.MysqlHandlerImpl;
import indi.uhyils.protocol.mysql.netty.MysqlNettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 08时45分
 */
@Component
public class MysqlNettyServerImpl extends ChannelInitializer<SocketChannel> implements MysqlNettyServer {

    @Value("${mysql-netty:3306}")
    private Integer port;

    /**
     * 主线程,单线程
     */
    private EventLoopGroup bossGroup;

    /**
     * 工作线程,多线程
     */
    private EventLoopGroup workerGroup;

    @PostConstruct
    @Override
    public void init() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
         .option(ChannelOption.SO_BACKLOG, 1024).childHandler(this);
        ChannelFuture f = b.bind(port).sync();

        f.channel().closeFuture();
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        // 由decoder解析 再交由handler处理
        ch.pipeline().addLast(new MysqlDecoderImpl(), new MysqlHandlerImpl());
    }
}
