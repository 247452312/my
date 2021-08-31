package indi.uhyils.protocol.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoCommand;
import indi.uhyils.service.LogMonitorJvmStatusService;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时33分
 */
public class RabbitJvmStatusInfoConsumer extends DefaultConsumer {


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
