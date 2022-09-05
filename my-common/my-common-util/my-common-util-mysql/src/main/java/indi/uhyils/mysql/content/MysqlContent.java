package indi.uhyils.mysql.content;

import indi.uhyils.MyThreadLocal;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import io.netty.channel.ChannelId;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 19时25分
 */
public class MysqlContent {

    /**
     * path中的分隔符
     */
    public static final String PATH_SEPARATOR = "/";

    /**
     * 默认库名称
     */
    public static final String CATALOG_NAME = "def";

    /**
     * 当前连接的mysql信息
     */
    public static final MyThreadLocal<MysqlTcpInfo> MYSQL_TCP_INFO = new MyThreadLocal<>();


    /**
     * 默认字符集
     */
    public static final String DEFAULT_CHARACTER_SET_NAME = "utf8mb4";

    /**
     * 数据库排序规则
     */
    public static final String DEFAULT_COLLATION_NAME = "utf8mb4_general_ci";

    /**
     * 系统默认的数据库,不允许创建,删除,修改
     */
    public static final List<String> SYS_DATABASE = new ArrayList<>();

    /**
     * 全局预处理语句id
     */
    private static AtomicLong PREPARE_ID = new AtomicLong(0);

    /**
     * mysql的tcp缓存
     */
    private static WeakHashMap<ChannelId, MysqlTcpInfo> mysqlTcpInfoWeakHashMap = new WeakHashMap<>();

    static {
        SYS_DATABASE.add("information_schema");
        SYS_DATABASE.add("mysql");
        SYS_DATABASE.add("performance_schema");
    }

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
