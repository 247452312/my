package indi.uhyils.protocol.mq.runner;

import indi.uhyils.context.DynamicContext;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.protocol.mq.DynamicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 消费者们
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时27分
 */
@Component
public class DynamicRunner implements ApplicationRunner {


    @Autowired
    private ApplicationContext applicationContext;

    @Value("${dynamic.mark:allService}")
    private String mark;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String exchangeName = DynamicContext.DYNAMIC_MQ_EXCHANGE_NAME;

        /* 启动接收dynamic信息的 */
        MqUtil.addConsumer(exchangeName, mark, RabbitMqContent.JVM_START_QUEUE_NAME, (channel) -> new DynamicConsumer(channel, applicationContext));

    }
}
