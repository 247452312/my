package indi.uhyils.runner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import indi.uhyils.pojo.rabbit.RabbitInterfaceCallInfoConsumer;
import indi.uhyils.pojo.rabbit.RabbitJvmStartInfoConsumer;
import indi.uhyils.pojo.rabbit.RabbitJvmStatusInfoConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 消费者们
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时27分
 */
@Component
public class JvmAndInterfaceCallInfoRunner implements ApplicationRunner {


    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private RabbitFactory rabbitFactory;

    private List<Channel> channels = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection conn = rabbitFactory.getConn();
        String exchangeName = RabbitMqContent.EXCHANGE_NAME;


        /* 第1个是启动JVM_START信息的 */
        Channel channel1 = conn.createChannel();
        String queueName1 = RabbitMqContent.JVM_START_QUEUE_NAME;
        channel1.exchangeDeclare(exchangeName, "direct", Boolean.FALSE, Boolean.FALSE, null);
        channel1.queueDeclare(queueName1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
        channel1.queueBind(queueName1, exchangeName, queueName1);
        channel1.basicConsume(queueName1, Boolean.FALSE, new RabbitJvmStartInfoConsumer(channel1, applicationContext));
        channels.add(channel1);

        /* 第2个是启动INTERFACE_CALL_INFO信息的 */
        Channel channel2 = conn.createChannel();
        String queueName2 = RabbitMqContent.INTERFACE_CALL_INFO;
        channel2.confirmSelect();
        channel2.exchangeDeclare(exchangeName, "direct", Boolean.FALSE, Boolean.FALSE, null);
        channel2.queueDeclare(queueName2, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
        channel2.queueBind(queueName2, exchangeName, queueName2);
        channel2.basicConsume(queueName2, true, new RabbitInterfaceCallInfoConsumer(channel2, applicationContext));
        channels.add(channel2);

        /* 第3个是启动JVM_STATUS信息的 */
        Channel channel3 = conn.createChannel();
        String queueName3 = RabbitMqContent.JVM_STATUS_QUEUE_NAME;
        channel3.exchangeDeclare(exchangeName, "direct", false, false, null);
        channel3.queueDeclare(queueName3, false, false, false, null);
        channel3.queueBind(queueName3, exchangeName, queueName3);
        channel3.basicConsume(queueName3, false, new RabbitJvmStatusInfoConsumer(channel3, applicationContext));
        channels.add(channel3);

    }
}
