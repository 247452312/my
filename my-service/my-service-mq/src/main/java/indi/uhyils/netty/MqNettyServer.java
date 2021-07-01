package indi.uhyils.netty;

import indi.uhyils.netty.handler.MqByteToMessageDecoder;
import indi.uhyils.netty.handler.ServiceExecuteHandler;
import indi.uhyils.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 11时30分
 * @Version 1.0
 */
@Component
public class MqNettyServer implements ApplicationRunner {

    @Value("${spring.mq.server.port:8080}")
    private int port;

    private void init() {
        // 主线程,单线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 工作线程,多线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast("decoder", new MqByteToMessageDecoder());
                    p.addLast("execute", new ServiceExecuteHandler());
                }
            });

            ChannelFuture sync = b.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LogUtil.info("MQ Netty 启动!! 端口:" + port);
        init();
    }
}
