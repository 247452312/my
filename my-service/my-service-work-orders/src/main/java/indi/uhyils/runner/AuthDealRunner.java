package indi.uhyils.runner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import indi.uhyils.content.OrderContent;
import indi.uhyils.handler.rabbit.OrderAutoDealConsumer;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 自动处理工单的地方
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 17时44分
 */
@Component
public class AuthDealRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RabbitFactory rabbitFactory;

    private List<Channel> channels = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection conn = rabbitFactory.getConn();
        String exchangeName = OrderContent.ORDER_EXCHANGE;

        /* 启动接收工单自动节点信息 */
        Channel channel = conn.createChannel();
        String queueName = OrderContent.ORDER_AUTO_NODE_SEND_QUEUE;
        channel.exchangeDeclare(exchangeName, "direct", false, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, queueName);
        channel.basicConsume(queueName, false, new OrderAutoDealConsumer(channel, applicationContext));
        channels.add(channel);
    }
}
