package indi.uhyils.protocol.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import indi.uhyils.annotation.MyMq;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoCommand;
import indi.uhyils.protocol.mq.base.AbstractMqConsumer;
import indi.uhyils.service.LogMonitorJvmStatusService;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.ApplicationContext;

/**
 * 监听JVM状态消息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时33分
 */
@MyMq(topic = RabbitMqContent.JVM_STATUS_QUEUE_NAME, tags = {RabbitMqContent.JVM_STATUS_QUEUE_NAME}, group = "监听JVM状态消息")
public class RabbitJvmStatusInfoConsumer extends AbstractMqConsumer {


    private LogMonitorJvmStatusService service;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel            the channel to which this consumer is attached
     * @param applicationContext
     */
    public RabbitJvmStatusInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        service = applicationContext.getBean(LogMonitorJvmStatusService.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        LogUtil.info(this, "接收到JVM状态信息");
        LogUtil.info(this, text);
        JvmStatusInfoCommand jvmStatusInfo = JSONObject.parseObject(text, JvmStatusInfoCommand.class);
        service.receiveJvmStatusInfo(jvmStatusInfo);
        // 获取tag(队列中的唯一标示)
        long deliveryTag = envelope.getDeliveryTag();
        // 确认 false为不批量确认
        getChannel().basicAck(deliveryTag, Boolean.FALSE);

    }
}
