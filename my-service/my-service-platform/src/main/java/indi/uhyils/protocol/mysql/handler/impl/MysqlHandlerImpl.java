package indi.uhyils.protocol.mysql.handler.impl;

import indi.uhyils.protocol.mysql.decoder.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.RequestAnalysis;
import indi.uhyils.protocol.mysql.pojo.request.MysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;


/**
 * mysql 请求处理类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时18分
 */
public class MysqlHandlerImpl extends ChannelInboundHandlerAdapter implements MysqlHandler {

    /**
     * 连接
     */
    private Channel mysqlChannel;

    /**
     * 客户端地址
     */
    private InetSocketAddress localAddress;

    /**
     * 创建完成之后默认是初见状态
     */
    private MysqlHandlerStatusEnum status = MysqlHandlerStatusEnum.FIRST_SIGHT;


    public MysqlHandlerImpl() {
    }

    /**
     * 获取连接状态
     *
     * @return
     */
    @Override
    public MysqlHandlerStatusEnum getStatus() {
        return status;
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
        //入口连接
        this.mysqlChannel = ctx.channel();
        this.localAddress = (InetSocketAddress) mysqlChannel.localAddress();
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
        byte[] mysqlBytes = (byte[]) msg;
        // 加载并解析请求
        MysqlRequest load = RequestAnalysis.load(this, mysqlBytes);
        if (load == null) {
            closeOnFlush();
            return;
        }
        // 执行请求并返回返回值
        MysqlResponse invoke = load.invoke();
        // 返回byte
        byte[] bytes = invoke.toByte();

        // 组装为netty看得懂的字节数组类型
        ByteBuf bufferToFlash = ctx.alloc().buffer(MysqlUtil.getSize(bytes));
        // 返回执行结果
        mysqlChannel.writeAndFlush(bufferToFlash);


    }

    /**
     * 异常时调用
     *
     * @param ctx
     * @param cause
     *
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LogUtil.error(cause, "mysql连接报错");
        closeOnFlush();
    }

    /**
     * 转发时调用
     *
     * @param ctx
     *
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        closeOnFlush();
    }

    /**
     * 关闭
     */
    @Override
    public void closeOnFlush() {
        if (mysqlChannel != null && mysqlChannel.isActive()) {
            mysqlChannel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
