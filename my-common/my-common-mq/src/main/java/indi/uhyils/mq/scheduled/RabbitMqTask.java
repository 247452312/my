package indi.uhyils.mq.scheduled;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import indi.uhyils.mq.util.JvmUtil;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * MQ发送者
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 07时38分
 */
@Component
@EnableScheduling
public class RabbitMqTask {

    /**
     * 这个微服务的唯一标示
     */
    @Autowired
    private volatile JvmUniqueMark jvmUniqueMark;

    @Resource
    private RabbitFactory rabbitFactory;

    private Channel startChannel;

    private Channel statusChannel;

    /**
     * 发送JVM start信息 并且发送第一次JVM状态信息
     *
     * @param channel       管道
     * @param jvmUniqueMark JVM唯一标示
     */
    public static void sendJvmStartInfo(Channel channel, JvmUniqueMark jvmUniqueMark) {
        if (channel == null) {
            return;
        }
        try {
            JvmStartInfo jvmStartInfo = JvmUtil.getJvmStartInfo(jvmUniqueMark);
            String s = JSON.toJSONString(jvmStartInfo);
            channel.basicPublish(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME, null, s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LogUtil.error(RabbitMqTask.class, e);
        }
    }

    /**
     * 发送JVM状态信息
     *
     * @param channel MQ管道
     */
    public static void sendJvmStatusInfo(Channel channel, JvmUniqueMark jvmUniqueMark) {
        if (channel == null) {
            return;
        }
        try {
            JvmStatusInfo jvmStatusInfo = JvmUtil.getJvmStatusInfo(jvmUniqueMark);
            String s = JSON.toJSONString(jvmStatusInfo);
            channel.basicPublish(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_STATUS_QUEUE_NAME, null, s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LogUtil.error(RabbitMqTask.class, e);
        }

    }

    /**
     * 从程序启动开始 每半小时执行一次
     *
     * @throws Exception MQ管道连接异常
     */
    @PostConstruct
    @Scheduled(cron = "0 */" + RabbitMqContent.OUT_TIME + " * * * ?")
    public void sendInfo() throws Exception {
        // 发送监控前主动gc一次
        System.gc();
        // 如果start信息没有发送过,那么发送start信息(只有项目启动时发送start信息失败时重复发送)
        if (!RabbitMqContent.getLogServiceOnLine()) {
            if (startChannel == null) {
                startChannel = rabbitFactory.getConn().createChannel();
                startChannel.confirmSelect();
                //创建exchange
                startChannel.exchangeDeclare(RabbitMqContent.EXCHANGE_NAME, "direct", false, false, null);
                //创建队列
                startChannel.queueDeclare(RabbitMqContent.JVM_START_QUEUE_NAME, false, false, false, null);
                //绑定exchange和queue
                startChannel.queueBind(RabbitMqContent.JVM_START_QUEUE_NAME, RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME);

                final RabbitMqTask rabbitMqTask = this;

                startChannel.addConfirmListener(new ConfirmListener() {
                    /**
                     * 消息处理成功
                     * @param deliveryTag 唯一标示
                     * @param multiple 未知
                     */
                    @Override
                    public void handleAck(long deliveryTag, boolean multiple) {
                        synchronized (this) {
                            LogUtil.info(this, "JVM启动消息处理成功(定时任务)");
                            // 设置唯一标示
                            rabbitMqTask.setJvmUniqueMark(jvmUniqueMark);
                            // 设置interface可以开始干活了
                            RabbitMqContent.setLogServiceOnLine(true);
                            // 设置为空 释放内存
                            JvmStartInfo.setStatusInfos(null);
                        }

                    }

                    /**
                     * 消息处理失败
                     * @param deliveryTag 唯一标识
                     * @param multiple 未知
                     */
                    @Override
                    public void handleNack(long deliveryTag, boolean multiple) {
                        LogUtil.warn(this, "启动信息处理失败(定时任务)");
                    }
                });
            }
            sendJvmStartInfo(startChannel, jvmUniqueMark);
        } else {
            // 否则正常发送
            if (statusChannel == null) {
                statusChannel = rabbitFactory.getConn().createChannel();
                statusChannel.confirmSelect();
                //创建exchange
                statusChannel.exchangeDeclare(RabbitMqContent.EXCHANGE_NAME, "direct", false, false, null);
                //创建队列
                statusChannel.queueDeclare(RabbitMqContent.JVM_STATUS_QUEUE_NAME, false, false, false, null);
                //绑定exchange和queue
                statusChannel.queueBind(RabbitMqContent.JVM_STATUS_QUEUE_NAME, RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_STATUS_QUEUE_NAME);
                statusChannel.addConfirmListener(new ConfirmListener() {
                    @Override
                    public void handleAck(long deliveryTag, boolean multiple) {
                        LogUtil.info(this, "JVM状态消息处理成功");
                        // 成功了就开启interface发送
                        RabbitMqContent.setLogServiceOnLine(true);

                    }

                    @Override
                    public void handleNack(long deliveryTag, boolean multiple) {
                        // 失败了就取消interface发送
                        RabbitMqContent.setLogServiceOnLine(false);
                        LogUtil.warn(this, "JVM信息处理失败");
                    }
                });
            }
            sendJvmStatusInfo(statusChannel, jvmUniqueMark);
        }
    }

    public JvmUniqueMark getJvmUniqueMark() {
        return jvmUniqueMark;
    }

    public void setJvmUniqueMark(JvmUniqueMark jvmUniqueMark) {
        this.jvmUniqueMark = jvmUniqueMark;
    }
}
