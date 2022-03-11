package indi.uhyils.protocol.mysql.handler.impl;

import indi.uhyils.exception.AssertException;
import indi.uhyils.protocol.mysql.decoder.impl.MysqlDecoderImpl;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.RequestAnalysis;
import indi.uhyils.protocol.mysql.pojo.entity.PrepareInfo;
import indi.uhyils.protocol.mysql.pojo.request.MysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.AuthResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * mysql 请求处理类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时18分
 */
public class MysqlHandlerImpl extends ChannelInboundHandlerAdapter implements MysqlHandler {

    /**
     * mysql 系统类
     */
    public static ThreadLocal<MysqlHandler> MYSQL_HANDLER = new ThreadLocal<>();

    /**
     * 预处理语句id
     */
    private static AtomicLong PREPARE_ID = new AtomicLong(0);

    /**
     * 预处理语句本地缓存
     */
    private static Map<Long, PrepareInfo> prepareCache = new ConcurrentHashMap<>();

    /**
     * client发过来的index
     */
    protected long index = -1;

    /**
     * 当前所在dbName
     */
    private String dbName;

    /**
     * 连接
     */
    private Channel mysqlChannel;

    /**
     * 客户端地址
     */
    private InetSocketAddress localAddress;

    /**
     * 错误数量
     */
    private int warnCount;

    /**
     * 创建完成之后默认是初见状态
     */
    private MysqlHandlerStatusEnum status = MysqlHandlerStatusEnum.FIRST_SIGHT;

    /**
     * 预处理语句id
     */
    private long prepareId;

    /**
     * 加密后的密码
     */
    private byte[] password;

    /**
     * 执行计划
     */
    private AtomicLong planIndex = new AtomicLong(0);

    private AtomicInteger loginIndex = new AtomicInteger(0);

    /**
     * 消费者信息
     */
    private ConsumerInfoDTO consumerInfoDTO;

    public MysqlHandlerImpl() {
    }

    /**
     * 获取连接状态
     *
     * @return
     */
    @Override
    public MysqlHandlerStatusEnum getAndIncrementStatus() {
        MysqlHandlerStatusEnum status = this.status;
        switch (status) {
            case FIRST_SIGHT:
                this.status = MysqlHandlerStatusEnum.PASSED;
                break;
            case OVER:
            case PASSED:
            default:
                break;
        }
        return status;
    }

    @Override
    public MysqlHandlerStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(MysqlHandlerStatusEnum status) {
        this.status = status;
    }

    @Override
    public long index() {
        return index;
    }

    @Override
    public void changeIndex(long index) {
        this.index = index;
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
        LogUtil.info("mysql 连接!" + localAddress);
        AuthResponse authResponse = new AuthResponse(this);
        List<byte[]> msgs = authResponse.toByte();
        for (byte[] msg : msgs) {
            LogUtil.info("mysql服务端发送握手信息:\n" + MysqlUtil.dump(msg));
            send(msg);
        }
    }

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
        String dump = MysqlUtil.dump(mysqlBytes);
        LogUtil.info("客户端发来请求:\n" + dump);
        try {
            MYSQL_HANDLER.set(this);
            // 加载并解析请求
            MysqlRequest load = RequestAnalysis.load(this, mysqlBytes);
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
            MysqlResponse response = new ErrResponse(this, MysqlErrCodeEnum.EE_UNKNOWN_PROTOCOL_OPTION, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, e.getLocalizedMessage());
            List<byte[]> bytes = response.toByte();
            for (byte[] aByte : bytes) {
                send(aByte);
            }
        } finally {
            MYSQL_HANDLER.remove();
        }
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

    @Override
    public int getWarnCount() {
        return warnCount;
    }

    @Override
    public void warnCountAdd() {
        warnCount++;
    }

    @Override
    public PrepareInfo getPrepareSql() {
        return prepareCache.remove(prepareId);
    }

    @Override
    public PrepareInfo getPrepareSql(long prepareId) {
        return prepareCache.remove(prepareId);
    }

    @Override
    public long setPrepareSql(String sql) {
        long andIncrement = PREPARE_ID.getAndIncrement();
        prepareCache.put(andIncrement, new PrepareInfo(sql));
        prepareId = andIncrement;
        return andIncrement;
    }

    @Override
    public byte[] getPassword() {
        return password;
    }

    @Override
    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public byte getLoginIndex() {
        return (byte) loginIndex.getAndAdd(2);
    }

    @Override
    public Channel getClientChannel() {
        return mysqlChannel;
    }

    @Override
    public String getDbName() {
        return dbName;
    }

    @Override
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public Long getAndAddPlanIndex(Integer count) {
        return planIndex.getAndAdd(count);
    }

    @Override
    public ConsumerInfoDTO getConsumerInfo() {
        return consumerInfoDTO;
    }

    @Override
    public void setConsumerInfo(ConsumerInfoDTO consumerInfoDTO) {
        this.consumerInfoDTO = consumerInfoDTO;
    }
}
