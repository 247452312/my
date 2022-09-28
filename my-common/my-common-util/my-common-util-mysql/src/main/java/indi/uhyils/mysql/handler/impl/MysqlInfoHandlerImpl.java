package indi.uhyils.mysql.handler.impl;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.exception.AssertException;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.decode.impl.MysqlDecoderImpl;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.handler.MysqlInfoHandler;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.cqe.MysqlCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComBinlogDumpCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComChangeUserCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComConnectCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComConnectOutCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComCreateDbCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComDebugCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComDelayedInsertCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComDropDbCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComFieldListCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComInitDbCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComPingCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComProcessInfoCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComProcessKillCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComQueryCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComQuitCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComRefreshCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComRegisterSlaveCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComSetOptionCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComShutdownCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComSleepCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStatisticsCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtCloseCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtExecuteCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtFetchCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtPrepareCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtResetCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComStmtSendLongDataCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComTableDumpCommand;
import indi.uhyils.mysql.pojo.cqe.impl.ComTimeCommand;
import indi.uhyils.mysql.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.AuthResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.service.MysqlSdkService;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.mysql.util.RequestAnalysis;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * mysql信息的处理器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时19分
 */
public class MysqlInfoHandlerImpl extends ChannelInboundHandlerAdapter implements MysqlInfoHandler, ChannelInboundHandler {

    /**
     * 对接平台服务
     */
    private final MysqlSdkService service;

    /**
     * mysql此次连接所缓存的信息
     */
    private MysqlTcpInfo mysqlTcpInfo;

    /**
     * 连接
     */
    private Channel mysqlChannel;

    public MysqlInfoHandlerImpl() {
        service = SpringUtil.getBean(MysqlSdkService.class);
        mysqlTcpInfo = new MysqlTcpInfo();
        MysqlContent.MYSQL_TCP_INFO.set(mysqlTcpInfo);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        MysqlContent.MYSQL_TCP_INFO.remove();
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
        //入口连接
        InetSocketAddress inetSocketAddress = (InetSocketAddress) mysqlChannel.localAddress();
        mysqlTcpInfo.setLocalAddress(inetSocketAddress);
        LogUtil.info("mysql 连接!" + inetSocketAddress);
        AuthResponse authResponse = new AuthResponse();
        List<byte[]> msgs = authResponse.toByte();
        mysqlTcpInfo.getAndIncrementStatus();
        mysqlTcpInfo.addIndex();
        // 缓存TCP信息到系统
        MysqlContent.putMysqlTcpInfo(mysqlChannel.id(), mysqlTcpInfo);

        for (byte[] msg : msgs) {
            LogUtil.info("mysql服务端初始发送握手信息:\n" + MysqlUtil.dump(msg));
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
         * 因{@link MysqlDecoderImpl#decode} 所以这里一定为byte[]
         */
        byte[] mysqlBytes = (byte[]) msg;

        // mysql 此次请求携带的信息
        MysqlThisRequestInfo mysqlThisRequestInfo = new MysqlThisRequestInfo(mysqlBytes);
        //初始化index为客户端发过来的index
        initIndex(mysqlThisRequestInfo);
        try {
            // 1.判断请求登录情况
            MysqlHandlerStatusEnum status = mysqlTcpInfo.getStatus();
            switch (status) {
                case FIRST_SIGHT:
                    // 第一次见,默认为登录请求
                    MysqlAuthCommand mysqlCommand = new MysqlAuthCommand(mysqlThisRequestInfo);
                    MysqlResponse invoke = service.login(mysqlCommand);
                    sendResponse(Collections.singletonList(invoke));
                    mysqlTcpInfo.getAndIncrementStatus();
                    return;
                case PASSED:
                    // 其他状态,正确接收请求
                    UserInfoHelper.setUser(mysqlTcpInfo.getUserDTO());
                    UserInfoHelper.setIp(mysqlTcpInfo.getLocalAddress().getAddress().getHostAddress());
                    try {
                        doDealRequest(mysqlThisRequestInfo);
                    } finally {
                        UserInfoHelper.clean();
                    }
                    return;
                case OVER:
                    // 已经结束,不再接收请求
                default:
                    Asserts.throwException("请求已经结束,请不要再次请求");
            }
        } catch (AssertException e) {
            LogUtil.error(e, "解析错误");
            MysqlResponse response = new ErrResponse(MysqlErrCodeEnum.EE_UNKNOWN_PROTOCOL_OPTION, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, e.getLocalizedMessage());
            List<byte[]> bytes = response.toByte();
            for (byte[] aByte : bytes) {
                send(aByte);
            }
            closeOnFlush();
        }
    }

    /**
     * 初始化index为客户端发过来的index
     *
     * @param mysqlThisRequestInfo
     */
    private void initIndex(MysqlThisRequestInfo mysqlThisRequestInfo) {
        final byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes);
        proto.getFixedInt(3);
        final long index = proto.getFixedInt(1);
        this.mysqlTcpInfo.changeIndexToClientIndex(index);
    }

    /**
     * 发送回应
     *
     * @param invokes
     */
    private void sendResponse(List<MysqlResponse> invokes) {
        if (CollectionUtil.isEmpty(invokes)) {
            return;
        }
        List<byte[]> finalResponse = new ArrayList<>();
        try {
            for (MysqlResponse mysqlResponse : invokes) {
                finalResponse.addAll(mysqlResponse.toByte());
            }
        } catch (AssertException ae) {
            LogUtil.error(ae);
            final byte[] bytes = MysqlUtil.mergeListBytes(new ErrResponse(MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, ae.getMessage()).toByte());
            String responseBytes = MysqlUtil.dump(bytes);
            LogUtil.info("mysql回应:\n" + responseBytes);
            send(bytes);
            return;
        } catch (Exception e) {
            LogUtil.error(this, e);
            final byte[] bytes = MysqlUtil.mergeListBytes(new ErrResponse(MysqlErrCodeEnum.EE_STAT, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "系统错误,请联系管理员或查询日志!").toByte());
            String responseBytes = MysqlUtil.dump(bytes);
            LogUtil.info("mysql回应:\n" + responseBytes);
            send(bytes);
            return;
        }
        final byte[] bytes = MysqlUtil.mergeListBytes(finalResponse);
        String responseBytes = MysqlUtil.dump(bytes);
        LogUtil.info("mysql回应:\n" + responseBytes);
        send(bytes);

    }

    /**
     * 处理请求
     *
     * @param mysqlThisRequestInfo
     *
     * @throws Exception
     */
    private void doDealRequest(MysqlThisRequestInfo mysqlThisRequestInfo) throws Exception {
        // 2.判断为已登录,加载并解析请求
        MysqlCommandTypeEnum load = RequestAnalysis.load(mysqlThisRequestInfo);
        if (load == null) {
            closeOnFlush();
            return;
        }
        // 3.根据请求获取结果
        List<MysqlResponse> invokes = null;
        try {
            invokes = loadCommand(mysqlThisRequestInfo, load);
        } catch (Exception e) {
            LogUtil.error(this, e);
            invokes = Collections.singletonList(new ErrResponse(MysqlErrCodeEnum.EE_UNKNOWN_PROTOCOL_OPTION, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, e.getLocalizedMessage()));
        }
        sendResponse(invokes);

    }

    /**
     * 关闭
     */
    public void closeOnFlush() {
        if (mysqlChannel != null && mysqlChannel.isActive()) {
            mysqlChannel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 根据不同请求获取结果
     *
     * @param mysqlThisRequestInfo
     * @param parse
     *
     * @return
     *
     * @throws Exception
     */
    private List<MysqlResponse> loadCommand(MysqlThisRequestInfo mysqlThisRequestInfo, MysqlCommandTypeEnum parse) throws Exception {
        MysqlCommand result = null;
        switch (parse) {
            /*这里是需要发送往后台进行处理的请求类型*/
            case COM_QUERY:
                // sql查询请求
                result = new ComQueryCommand(mysqlThisRequestInfo);
                break;
            case COM_FIELD_LIST:
                // 字段获取请求
                result = new ComFieldListCommand(mysqlThisRequestInfo);
                break;
            case COM_TABLE_DUMP:
                // 表结构获取请求
                result = new ComTableDumpCommand(mysqlThisRequestInfo);
                break;
            case COM_STMT_EXECUTE:
                // 执行预处理语句
                result = new ComStmtExecuteCommand(mysqlThisRequestInfo);
                break;

            /*以下是不需要发送往服务器进行处理的请求类型*/

            /* 这里是和服务器相关的请求类型*/
            case COM_PROCESS_INFO:
                result = new ComProcessInfoCommand(mysqlThisRequestInfo);
                break;
            case COM_PROCESS_KILL:
                result = new ComProcessKillCommand(mysqlThisRequestInfo);
                break;
            case COM_STATISTICS:
                result = new ComStatisticsCommand(mysqlThisRequestInfo);
                break;
            /*正常请求*/
            case COM_STMT_SEND_LONG_DATA:
                result = new ComStmtSendLongDataCommand(mysqlThisRequestInfo);
                break;
            case COM_STMT_PREPARE:
                result = new ComStmtPrepareCommand(mysqlThisRequestInfo);
                break;
            case COM_PING:
                result = new ComPingCommand(mysqlThisRequestInfo);
                break;
            case COM_QUIT:
                result = new ComQuitCommand(mysqlThisRequestInfo);
                break;
            case COM_TIME:
                result = new ComTimeCommand(mysqlThisRequestInfo);
                break;
            case COM_DEBUG:
                result = new ComDebugCommand(mysqlThisRequestInfo);
                break;
            case COM_SLEEP:
                result = new ComSleepCommand(mysqlThisRequestInfo);
                break;
            case COM_CONNECT:
                result = new ComConnectCommand(mysqlThisRequestInfo);
                break;
            case COM_DROP_DB:
                result = new ComDropDbCommand(mysqlThisRequestInfo);
                break;
            case COM_INIT_DB:
                result = new ComInitDbCommand(mysqlThisRequestInfo);
                break;
            case COM_REFRESH:
                result = new ComRefreshCommand(mysqlThisRequestInfo);
                break;
            case COM_SHUTDOWN:
                result = new ComShutdownCommand(mysqlThisRequestInfo);
                break;
            case COM_CREATE_DB:
                result = new ComCreateDbCommand(mysqlThisRequestInfo);
                break;
            case COM_SET_OPTION:
                result = new ComSetOptionCommand(mysqlThisRequestInfo);
                break;
            case COM_STMT_CLOSE:
                result = new ComStmtCloseCommand(mysqlThisRequestInfo);
                break;
            case COM_STMT_FETCH:
                result = new ComStmtFetchCommand(mysqlThisRequestInfo);
                break;
            case COM_STMT_RESET:
                result = new ComStmtResetCommand(mysqlThisRequestInfo);
                break;
            case COM_BINLOG_DUMP:
                result = new ComBinlogDumpCommand(mysqlThisRequestInfo);
                break;
            case COM_CHANGE_USER:
                result = new ComChangeUserCommand(mysqlThisRequestInfo);
                break;
            case COM_CONNECT_OUT:
                result = new ComConnectOutCommand(mysqlThisRequestInfo);
                break;
            case COM_DELAYED_INSERT:
                result = new ComDelayedInsertCommand(mysqlThisRequestInfo);
                break;
            case COM_REGISTER_SLAVE:
                result = new ComRegisterSlaveCommand(mysqlThisRequestInfo);
                break;
            default:
                break;
        }

        return result.invoke();
    }

}
