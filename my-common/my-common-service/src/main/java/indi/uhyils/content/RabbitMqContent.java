package indi.uhyils.content;

import indi.uhyils.util.IpUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 07时07分
 */
public class RabbitMqContent {


    /**
     * exchange 路由名称
     */
    public static final String EXCHANGE_NAME = "JVM_LOG";

    /**
     * JVM 开启通知管道
     */
    public static final String JVM_START_QUEUE_NAME = "jvm_start";


    /**
     * JVM状态信息
     */
    public static final String JVM_STATUS_QUEUE_NAME = "jvm_status";

    /**
     * 服务调用日志
     */
    public static final String INTERFACE_CALL_INFO = "interface_call_info";


    /**
     * log服务是否在线
     */
    private static volatile Boolean logServiceOnLine = false;

    public static final String IP;
    public static final Long START_TIME;

    static {
        IP = IpUtil.getIp();
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        START_TIME = bean.getStartTime();
    }


    public static void init() {

    }

    public static Boolean getLogServiceOnLine() {
        return logServiceOnLine;
    }

    public static void setLogServiceOnLine(Boolean logServiceOnLine) {
        RabbitMqContent.logServiceOnLine = logServiceOnLine;
    }
}
