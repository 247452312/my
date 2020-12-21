package indi.uhyils.rpc.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 16时10分
 */
public class RpcPrintOutHandler implements ChannelOutboundHandler {

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:bind");
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:connect");

    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:disconnect");

    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:close");

    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:deregister");

    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RpcPrintOutHandler:read");

    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("RpcPrintOutHandler:write");

    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RpcPrintOutHandler:flush");

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RpcPrintOutHandler:handlerAdded");

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RpcPrintOutHandler:handlerRemoved");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("RpcPrintOutHandler:exceptionCaught");

    }
}
