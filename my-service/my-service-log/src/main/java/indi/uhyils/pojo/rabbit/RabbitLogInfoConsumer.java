package indi.uhyils.pojo.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.nio.charset.StandardCharsets;
import org.springframework.context.ApplicationContext;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月29日 14时14分
 */
public class RabbitLogInfoConsumer extends DefaultConsumer {


    /**
     * @param channel
     * @param applicationContext
     */
    public RabbitLogInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        String text = new String(body, StandardCharsets.UTF_8);
        System.out.println(text);

    }

}
