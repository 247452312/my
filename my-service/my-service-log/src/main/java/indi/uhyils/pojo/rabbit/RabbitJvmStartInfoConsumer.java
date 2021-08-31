package indi.uhyils.pojo.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfoEvent;
import indi.uhyils.service.LogMonitorService;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时33分
 */
public class RabbitJvmStartInfoConsumer extends DefaultConsumer {

    private LogMonitorService logMonitorService;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel            the channel to which this consumer is attached
     * @param applicationContext
     */
    public RabbitJvmStartInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        logMonitorService = applicationContext.getBean(LogMonitorService.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        JvmStartInfoEvent jvmStartInfo = JSONObject.parseObject(text, JvmStartInfoEvent.class);
        LogUtil.info(this, "接收到JVM启动信息");
        LogUtil.info(this, text);
        logMonitorService.receiveJvmStartInfo(jvmStartInfo);

        // 获取tag(队列中的唯一标示)
        long deliveryTag = envelope.getDeliveryTag();
        // 确认 false为不批量确认
        getChannel().basicAck(deliveryTag, false);
    }
}
