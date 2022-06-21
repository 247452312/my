package indi.uhyils.mq.util;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月29日 12时38分
 */
public class LogInfoSendMqUtil {

    private static final String EXCHANGE_NAME = "log_exchange";

    private static final String QUEUE_NAME = "log_queue";


    /**
     * 发送日志信息
     *
     * @return
     */
    public static void sendLogInfo(String msg) {
        MqUtil.sendMsgNoLog(EXCHANGE_NAME, QUEUE_NAME, msg);
    }

    public static String getExchangeName() {
        return EXCHANGE_NAME;
    }

    public static String getQueueName() {
        return QUEUE_NAME;
    }

}
