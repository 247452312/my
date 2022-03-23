package indi.uhyils.protocol.mysql.handler.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.enums.MysqlErrCodeEnum;
import indi.uhyils.enums.MysqlHandlerStatusEnum;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.exception.AssertException;
import indi.uhyils.pojo.cqe.MysqlCommand;
import indi.uhyils.pojo.cqe.impl.ComBinlogDumpCommand;
import indi.uhyils.pojo.cqe.impl.ComChangeUserCommand;
import indi.uhyils.pojo.cqe.impl.ComConnectCommand;
import indi.uhyils.pojo.cqe.impl.ComConnectOutCommand;
import indi.uhyils.pojo.cqe.impl.ComCreateDbCommand;
import indi.uhyils.pojo.cqe.impl.ComDebugCommand;
import indi.uhyils.pojo.cqe.impl.ComDelayedInsertCommand;
import indi.uhyils.pojo.cqe.impl.ComDropDbCommand;
import indi.uhyils.pojo.cqe.impl.ComFieldListCommand;
import indi.uhyils.pojo.cqe.impl.ComInitDbCommand;
import indi.uhyils.pojo.cqe.impl.ComPingCommand;
import indi.uhyils.pojo.cqe.impl.ComProcessInfoCommand;
import indi.uhyils.pojo.cqe.impl.ComProcessKillCommand;
import indi.uhyils.pojo.cqe.impl.ComQueryCommand;
import indi.uhyils.pojo.cqe.impl.ComQuitCommand;
import indi.uhyils.pojo.cqe.impl.ComRefreshCommand;
import indi.uhyils.pojo.cqe.impl.ComRegisterSlaveCommand;
import indi.uhyils.pojo.cqe.impl.ComSetOptionCommand;
import indi.uhyils.pojo.cqe.impl.ComShutdownCommand;
import indi.uhyils.pojo.cqe.impl.ComSleepCommand;
import indi.uhyils.pojo.cqe.impl.ComStatisticsCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtCloseCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtExecuteCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtFetchCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtPrepareCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtResetCommand;
import indi.uhyils.pojo.cqe.impl.ComStmtSendLongDataCommand;
import indi.uhyils.pojo.cqe.impl.ComTableDumpCommand;
import indi.uhyils.pojo.cqe.impl.ComTimeCommand;
import indi.uhyils.pojo.cqe.impl.MysqlAuthCommand;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.pojo.response.impl.AuthResponse;
import indi.uhyils.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.decode.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.handler.MysqlInfoHandler;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.protocol.mysql.util.RequestAnalysis;
import indi.uhyils.service.PlatformPublishNodeService;
import indi.uhyils.service.PlatformService;
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
     * 发布节点服务
     */
    private PlatformPublishNodeService platformPublishNodeService;

    /**
     * 对接平台服务
     */
    private PlatformService service;

    public MysqlInfoHandlerImpl() {
        platformPublishNodeService = SpringUtil.getBean(platformPublishNodeService.getClass());
    }

    private List<MysqlResponse> loadCommand(MysqlThisRequestInfo mysqlThisRequestInfo, MysqlCommandTypeEnum parse) throws Exception {
        MysqlCommand result = null;
        switch (parse) {
            /*这里是需要发送往后台进行处理的请求类型*/
            case COM_QUERY:
                result = new ComQueryCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                result.load(mysqlThisRequestInfo);
                break;
            case COM_FIELD_LIST:
                result = new ComFieldListCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_TABLE_DUMP:
                result = new ComTableDumpCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_EXECUTE:
                result = new ComStmtExecuteCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_PREPARE:
                result = new ComStmtPrepareCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_SEND_LONG_DATA:
                result = new ComStmtSendLongDataCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;

            /* 这里是和服务器相关的请求类型*/
            case COM_PROCESS_INFO:
                result = new ComProcessInfoCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_PROCESS_KILL:
                result = new ComProcessKillCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STATISTICS:
                result = new ComStatisticsCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;


            /*这里是不需要发送往服务器进行处理的请求类型*/
            case COM_PING:
                result = new ComPingCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_QUIT:
                result = new ComQuitCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                mysqlTcpInfo.setStatus(MysqlHandlerStatusEnum.OVER);
                break;
            case COM_TIME:
                result = new ComTimeCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_DEBUG:
                result = new ComDebugCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_SLEEP:
                result = new ComSleepCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_CONNECT:
                result = new ComConnectCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_DROP_DB:
                result = new ComDropDbCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_INIT_DB:
                result = new ComInitDbCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_REFRESH:
                result = new ComRefreshCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_SHUTDOWN:
                result = new ComShutdownCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_CREATE_DB:
                result = new ComCreateDbCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_SET_OPTION:
                result = new ComSetOptionCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_CLOSE:
                result = new ComStmtCloseCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_FETCH:
                result = new ComStmtFetchCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_STMT_RESET:
                result = new ComStmtResetCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_BINLOG_DUMP:
                result = new ComBinlogDumpCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_CHANGE_USER:
                result = new ComChangeUserCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_CONNECT_OUT:
                result = new ComConnectOutCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_DELAYED_INSERT:
                result = new ComDelayedInsertCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            case COM_REGISTER_SLAVE:
                result = new ComRegisterSlaveCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                break;
            default:
                break;
        }

        return result.invoke();
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
        AuthResponse authResponse = new AuthResponse(mysqlTcpInfo);
        List<byte[]> msgs = authResponse.toByte();
        mysqlTcpInfo.addIndex();
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
                    MysqlAuthCommand mysqlCommand = new MysqlAuthCommand(mysqlTcpInfo, mysqlThisRequestInfo);
                    mysqlCommand.load(mysqlThisRequestInfo);
                    MysqlResponse invoke = service.login(mysqlCommand);
                    sendResponse(invoke);
                    return;
                case PASSED:
                    doDealRequest(mysqlThisRequestInfo);
                    return;
                case OVER:
                default:
                    Asserts.throwException("请求已经结束,请不要再次请求");
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

    private void doDealRequest(MysqlThisRequestInfo mysqlThisRequestInfo) throws Exception {
        // 2.判断为已登录,加载并解析请求
        MysqlCommandTypeEnum load = RequestAnalysis.load(mysqlThisRequestInfo);
        if (load == null) {
            closeOnFlush();
            return;
        }
        List<MysqlResponse> invokes = loadCommand(mysqlThisRequestInfo, load);
        sendResponse(invokes);

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
        for (MysqlResponse invoke : invokes) {
            sendResponse(invoke);
        }
    }

    /**
     * 发送回应
     *
     * @param invoke
     */
    private void sendResponse(MysqlResponse invoke) {
        // 返回byte
        List<byte[]> bytes = invoke.toByte();
        for (byte[] aByte : bytes) {
            String responseBytes = MysqlUtil.dump(aByte);
            LogUtil.info("mysql回应:\n" + responseBytes);
            send(aByte);
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
