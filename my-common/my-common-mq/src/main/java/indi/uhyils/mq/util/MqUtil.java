package indi.uhyils.mq.util;

import com.rabbitmq.client.Channel;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import indi.uhyils.util.ObjectByteUtil;
import indi.uhyils.util.SpringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 14时11分
 */
public class MqUtil {

    private static final Map<MqQueueInfo, Channel> CHANNEL_MAP = new HashMap<>();
    /**
     * 创建channel时的锁
     */
    private static Lock newChannelLock = new ReentrantLock();

    private MqUtil() {
    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param bytes    发送的信息的byte
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static void sendMsg(String exchange, String queue, byte[] bytes) throws IOException, TimeoutException {
        Channel channel;
        MqQueueInfo key = new MqQueueInfo(exchange, queue);
        if (CHANNEL_MAP.containsKey(key)) {
            channel = CHANNEL_MAP.get(key);
        } else {
            newChannelLock.lock();
            try {
                if (!CHANNEL_MAP.containsKey(key)) {
                    RabbitFactory factory = SpringUtil.getBean(RabbitFactory.class);
                    channel = factory.getConn().createChannel();
                    //创建exchange
                    channel.exchangeDeclare(exchange, "direct", false, false, null);
                    //创建队列
                    channel.queueDeclare(queue, false, false, false, null);
                    //绑定exchange和queue
                    channel.queueBind(queue, exchange, queue);
                    CHANNEL_MAP.put(key, channel);
                } else {
                    channel = CHANNEL_MAP.get(key);
                }
            } finally {
                newChannelLock.unlock();
            }
        }
        channel.basicPublish(exchange, queue, null, bytes);

    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param msg      发送的信息的byte
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static void sendMsg(String exchange, String queue, Object msg) throws IOException, TimeoutException {
        sendMsg(exchange, queue, ObjectByteUtil.toByteArray(msg));
    }

    static class MqQueueInfo {
        /**
         * 路由
         */
        private String exchange;
        /**
         * 队列
         */
        private String queue;

        public MqQueueInfo(String exchange, String queue) {
            this.exchange = exchange;
            this.queue = queue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return Boolean.TRUE;
            }
            if (o == null || getClass() != o.getClass()) {
                return Boolean.FALSE;
            }
            MqQueueInfo that = (MqQueueInfo) o;
            return Objects.equals(exchange, that.exchange) &&
                    Objects.equals(queue, that.queue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(exchange, queue);
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getQueue() {
            return queue;
        }

        public void setQueue(String queue) {
            this.queue = queue;
        }
    }
}
