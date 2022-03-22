package indi.uhyils.protocol.mysql.handler.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlHandlerStatusEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.exception.AssertException;
import indi.uhyils.protocol.mysql.decode.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.handler.MysqlInfoHandler;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.protocol.mysql.util.RequestAnalysis;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.mysql.pojo.request.MysqlRequest;
import indi.uhyils.util.mysql.pojo.request.impl.MysqlAuthRequest;
import indi.uhyils.util.mysql.pojo.response.MysqlResponse;
import indi.uhyils.util.mysql.pojo.response.impl.AuthResponse;
import indi.uhyils.util.mysql.pojo.response.impl.ErrResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * mysql信息的处理器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时19分
 */
public class MysqlInfoHandlerImpl extends ChannelInboundHandlerAdapter implements MysqlInfoHandler, ChannelInboundHandler {

    /**
     * mysql此次连接所缓存的信息
     */
    private MysqlTcpInfo mysqlTcpInfo = new MysqlTcpInfo();

    /**
     * 连接
     */
    private Channel mysqlChannel;

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
        //入口连接
        InetSocketAddress inetSocketAddress = (InetSocketAddress) mysqlChannel.localAddress();
        mysqlTcpInfo.setLocalAddress(inetSocketAddress);
        LogUtil.info("mysql 连接!" + inetSocketAddress);
        long index = mysqlTcpInfo.index();
        AuthResponse authResponse = new AuthResponse(index);
        List<byte[]> msgs = authResponse.toByte();

        mysqlTcpInfo.setIndex(index + 1);
        for (byte[] msg : msgs) {
            LogUtil.info("mysql服务端发送握手信息:\n" + MysqlUtil.dump(msg));
            send(msg);
        }
    }

    /**
     * 发送数据
     *
     * @param msg
     */
    private void send(byte[] msg) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg);
        this.mysqlChannel.writeAndFlush(buf);
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
         * 因{@link MysqlDecoderImpl#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)} 所以这里一定为byte[]
         */
        byte[] mysqlBytes = (byte[]) msg;

        // mysql 此次请求携带的信息
        MysqlThisRequestInfo mysqlThisRequestInfo = new MysqlThisRequestInfo(mysqlBytes);

        try {
            // 1.判断请求登录情况
            MysqlHandlerStatusEnum status = mysqlTcpInfo.getAndIncrementStatus();
            switch (status) {
                case FIRST_SIGHT:
                    MysqlRequest mysqlRequest = new MysqlAuthRequest(mysqlTcpInfo);
                case PASSED:
                case OVER:
                default:
                    Asserts.throwException("请求已经结束,请不要再次请求");
            }

            // 2.判断为已登录,加载并解析请求
            MysqlCommandTypeEnum load = RequestAnalysis.load(mysqlBytes);
            if (load == null) {
                closeOnFlush();
                return;
            }
            // 执行请求并返回返回值
            List<MysqlResponse> invokes = load.invoke();
            if (CollectionUtil.isEmpty(invokes)) {
                return;
            }
            for (MysqlResponse invoke : invokes) {
                // 返回byte
                List<byte[]> bytes = invoke.toByte();
                for (byte[] aByte : bytes) {
                    String responseBytes = MysqlUtil.dump(aByte);
                    LogUtil.info("mysql回应:\n" + responseBytes);
                    send(aByte);
                }
            }
        } catch (AssertException e) {
            LogUtil.error(e, "解析错误");
            MysqlResponse response = new ErrResponse(mysqlTcpInfo, MysqlErrCodeEnum.EE_UNKNOWN_PROTOCOL_OPTION, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, e.getLocalizedMessage());
            List<byte[]> bytes = response.toByte();
            for (byte[] aByte : bytes) {
                send(aByte);
            }
            closeOnFlush();
        }
    }


    /**
     * 关闭
     */
    public void closeOnFlush() {
        if (mysqlChannel != null && mysqlChannel.isActive()) {
            mysqlChannel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
