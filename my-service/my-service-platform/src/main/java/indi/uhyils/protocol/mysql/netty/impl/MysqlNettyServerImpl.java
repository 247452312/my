package indi.uhyils.protocol.mysql.netty.impl;

import indi.uhyils.protocol.mysql.decode.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.handler.impl.MysqlInfoHandlerImpl;
import indi.uhyils.protocol.mysql.netty.MysqlNettyServer;
import indi.uhyils.util.LogUtil;
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

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 09时18分
 */
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
        ch.pipeline().addLast(new MysqlDecoderImpl(), new MysqlInfoHandlerImpl());
    }

}
