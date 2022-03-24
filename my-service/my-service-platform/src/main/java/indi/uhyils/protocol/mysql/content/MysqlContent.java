package indi.uhyils.protocol.mysql.content;

import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import io.netty.channel.ChannelId;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时25分
 */
public class MysqlContent {

    /**
     * 全局预处理语句id
     */
    private static AtomicLong PREPARE_ID = new AtomicLong(0);

    /**
     * mysql的tcp缓存
     */
    private static WeakHashMap<ChannelId, MysqlTcpInfo> mysqlTcpInfoWeakHashMap = new WeakHashMap<>();

    public static long getAndIncrementPrepareId() {
        return PREPARE_ID.getAndIncrement();
    }

    /**
     * 提交新的tcp连接信息
     *
     * @param channelId
     * @param mysqlTcpInfo
     *
     * @return
     */
    public static MysqlTcpInfo putMysqlTcpInfo(ChannelId channelId, MysqlTcpInfo mysqlTcpInfo) {
        return mysqlTcpInfoWeakHashMap.put(channelId, mysqlTcpInfo);
    }

}
