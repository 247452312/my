package indi.uhyils.netty;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import indi.uhyils.netty.handler.MqByteToMessageDecoder;
import indi.uhyils.netty.handler.ServiceExecuteHandler;
import indi.uhyils.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 11时30分
 * @Version 1.0
 */
public class MqNettyServer implements ApplicationRunner {
    /**
     * 主线程,单线程
     */
    private EventLoopGroup bossGroup;
    /**
     * 工作线程,多线程
     */
    private EventLoopGroup workerGroup;

    private void init() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast("decoder", new MqByteToMessageDecoder());
                        p.addLast("execute", new ServiceExecuteHandler());
                    }
                });

            b.bind(8080).sync();
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
}
