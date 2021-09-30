package indi.uhyils.mq.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Supplier;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.context.MyTraceIdContext;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import indi.uhyils.pojo.other.RpcTraceInfo;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 14时11分
 */
public class MqUtil {

    private static final Map<MqQueueInfo, Channel> CHANNEL_MAP = new HashMap<>();

    /**
     * 保存consumer
     */
    private static final Map<String, Consumer> consumers = new HashMap<>();

    /**
     * 创建channel时的锁
     */
    private static Lock newChannelLock = new ReentrantLock();

    private MqUtil() {
    }

    /**
     * 添加消费者
     *
     * @param exchange         路由的名称
     * @param queue            队列的名称
     * @param name             消费者的名称
     * @param consumerFunction 消费者创建逻辑
     */
    public static <T extends Consumer> T addConsumer(String exchange, String queue, String name, Function<Channel, T> consumerFunction) throws IOException, TimeoutException {
        RabbitFactory factory = SpringUtil.getBean(RabbitFactory.class);
        Connection conn = factory.getConn();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(exchange, "direct", Boolean.FALSE, Boolean.FALSE, null);
        channel.queueDeclare(queue, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
        channel.queueBind(queue, exchange, queue);
        T consumer = consumerFunction.apply(channel);
        T proxyInstance = (T) Proxy.newProxyInstance(consumer.getClass().getClassLoader(), new Class[]{Consumer.class}, new MqInvocationHandler(consumer));
        channel.basicConsume(queue, Boolean.FALSE, proxyInstance);
        consumers.put(name, consumer);
        return proxyInstance;
    }

    /**
     * 添加消费者
     *
     * @param exchange         路由的名称
     * @param queue            队列的名称
     * @param name             消费者的名称
     * @param consumerFunction 消费者创建逻辑
     */
    public static <T extends Consumer> T addConsumer(String exchange, String queue, String name, Class<T> interfaceClass, Function<Channel, ? extends T> consumerFunction) throws IOException, TimeoutException {
        RabbitFactory factory = SpringUtil.getBean(RabbitFactory.class);
        Connection conn = factory.getConn();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(exchange, "direct", Boolean.FALSE, Boolean.FALSE, null);
        channel.queueDeclare(queue, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
        channel.queueBind(queue, exchange, queue);
        Consumer consumer = consumerFunction.apply(channel);
        T proxyInstance = (T) Proxy.newProxyInstance(consumer.getClass().getClassLoader(), new Class[]{interfaceClass}, new MqInvocationHandler(consumer));
        channel.basicConsume(queue, Boolean.TRUE, proxyInstance);
        consumers.put(name, consumer);
        return proxyInstance;
    }

    /**
     * 添加消费者
     *
     * @param exchange         路由的名称
     * @param queue            队列的名称
     * @param name             消费者的名称
     * @param consumerFunction 消费者创建逻辑
     */
    public static void addNoLogConsumer(String exchange, String queue, String name, Function<Channel, Consumer> consumerFunction) throws IOException, TimeoutException {
        RabbitFactory factory = SpringUtil.getBean(RabbitFactory.class);
        Connection conn = factory.getConn();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(exchange, "direct", Boolean.FALSE, Boolean.FALSE, null);
        channel.queueDeclare(queue, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
        channel.queueBind(queue, exchange, queue);
        Consumer consumer = consumerFunction.apply(channel);
        channel.basicConsume(queue, Boolean.TRUE, consumer);
        consumers.put(name, consumer);
    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param bytes    发送的信息的byte
     *
     * @return
     */
    private static void sendMsg(String exchange, String queue, byte[] bytes) {
        Supplier direct = () -> {
            doSendMsg(exchange, queue, bytes);
            return null;
        };
        MyTraceIdContext.printLogInfo(LogTypeEnum.MQ, direct, new String[]{exchange, queue}, exchange, queue);

    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param msg      发送的信息的byte
     *
     * @return
     */
    public static void sendMsg(String exchange, String queue, String msg) {
        MqSendInfo build = MqSendInfo.build(msg, RpcTraceInfo.build(MyTraceIdContext.getThraceId(), MyTraceIdContext.getNextRpcIds()));
        byte[] bytes = JSON.toJSONString(build).getBytes(StandardCharsets.UTF_8);
        sendMsg(exchange, queue, bytes);
    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param msg      发送的信息的byte
     *
     * @return
     */
    protected static void sendMsgNoLog(String exchange, String queue, String msg) {
        doSendMsg(exchange, queue, JSON.toJSONString(msg).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 发送信息
     *
     * @param exchange
     * @param queue
     * @param bytes
     */
    private static void doSendMsg(String exchange, String queue, byte[] bytes) {

        MqQueueInfo key = new MqQueueInfo(exchange, queue);
        Channel channel = null;
        if (CHANNEL_MAP.containsKey(key)) {
            channel = CHANNEL_MAP.get(key);
        } else {
            newChannelLock.lock();
            try {
                if (!CHANNEL_MAP.containsKey(key)) {
                    RabbitFactory factory = null;
                    try {
                        factory = SpringUtil.getBean(RabbitFactory.class);
                    } catch (Exception e) {
                        LogUtil.error(e);
                    }
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

            } catch (TimeoutException | IOException e) {
                LogUtil.error(e);
            } finally {
                newChannelLock.unlock();
            }
        }
        try {
            channel.basicPublish(exchange, queue, null, bytes);
        } catch (IOException e) {
            LogUtil.error(e);
        }
    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param bytes    发送的信息的byte
     *
     * @return
     *
     * @throws IOException
     * @throws TimeoutException
     */
    private static void sendConfirmMsg(String exchange, String queue, ConfirmListener listener, byte[] bytes) throws IOException, TimeoutException {

        Supplier direct = () -> {
            MqQueueInfo key = new MqQueueInfo(exchange, queue);
            Channel channel = null;
            if (CHANNEL_MAP.containsKey(key)) {
                channel = CHANNEL_MAP.get(key);
            } else {
                newChannelLock.lock();
                try {
                    if (!CHANNEL_MAP.containsKey(key)) {
                        RabbitFactory factory = SpringUtil.getBean(RabbitFactory.class);
                        channel = factory.getConn().createChannel();
                        channel.confirmSelect();
                        //创建exchange
                        channel.exchangeDeclare(exchange, "direct", false, false, null);
                        //创建队列
                        channel.queueDeclare(queue, false, false, false, null);
                        //绑定exchange和queue
                        channel.queueBind(queue, exchange, queue);
                        channel.addConfirmListener(listener);
                        CHANNEL_MAP.put(key, channel);
                    } else {
                        channel = CHANNEL_MAP.get(key);
                    }
                } catch (TimeoutException | IOException e) {
                    LogUtil.error(e);
                } finally {
                    newChannelLock.unlock();
                }
            }
            try {
                channel.basicPublish(exchange, queue, null, bytes);
            } catch (IOException e) {
                LogUtil.error(e);
            }
            return null;
        };
        MyTraceIdContext.printLogInfo(LogTypeEnum.MQ, direct, new String[]{exchange, queue}, exchange, queue);
    }

    /**
     * 推送信息到mq
     *
     * @param exchange 路由名称
     * @param queue    队列名称
     * @param listener 回应监听
     * @param msg      发送的信息
     *
     * @return
     */
    public static void sendConfirmMsg(String exchange, String queue, ConfirmListener listener, String msg) {
        MyTraceIdContext.printLogInfo(LogTypeEnum.MQ, () -> {
            MqSendInfo build = MqSendInfo.build(msg, RpcTraceInfo.build(MyTraceIdContext.getThraceId(), MyTraceIdContext.getNextRpcIds()));
            byte[] bytes = JSON.toJSONString(build).getBytes(StandardCharsets.UTF_8);
            try {
                sendConfirmMsg(exchange, queue, listener, bytes);
            } catch (Exception e) {
                LogUtil.error(e);
            }
            return null;
        }, new String[]{exchange, queue}, exchange, queue);

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

    private static class MqSendInfo {

        private String json;

        private RpcTraceInfo rpcInfo;

        public static MqSendInfo build(String json, RpcTraceInfo rpcInfo) {
            MqSendInfo build = new MqSendInfo();
            build.json = json;
            build.rpcInfo = rpcInfo;
            return build;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public RpcTraceInfo getRpcInfo() {
            return rpcInfo;
        }

        public void setRpcInfo(RpcTraceInfo rpcInfo) {
            this.rpcInfo = rpcInfo;
        }
    }

    private static class MqInvocationHandler implements InvocationHandler {

        private static final String RECEIVE_METHOD_NAME = "handleDelivery";

        private final Consumer consumer;

        public MqInvocationHandler(Consumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals(RECEIVE_METHOD_NAME)) {
                String consumerTag = (String) args[0];
                Envelope envelope = (Envelope) args[1];
                AMQP.BasicProperties properties = (BasicProperties) args[2];
                byte[] bytes = (byte[]) args[3];
                String json = new String(bytes, StandardCharsets.UTF_8);
                MqSendInfo mqSendInfo = JSON.parseObject(json, MqSendInfo.class);
                RpcTraceInfo rpcInfo = mqSendInfo.getRpcInfo();

                String realInfo = mqSendInfo.getJson();
                byte[] realBytes = realInfo.getBytes(StandardCharsets.UTF_8);
                MyTraceIdContext.setThraceId(rpcInfo.getTraceId());
                MyTraceIdContext.setRpcId(rpcInfo.getRpcIds());
                try {
                    return method.invoke(consumer, consumerTag, envelope, properties, realBytes);
                } finally {
                    MyTraceIdContext.clean();
                }
            } else {
                return method.invoke(consumer, args);
            }
        }
    }
}
