package indi.uhyils.protocol.mq.runner;

import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.util.LogInfoSendMqUtil;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.protocol.mq.RabbitJvmStartInfoConsumer;
import indi.uhyils.protocol.mq.RabbitJvmStatusInfoConsumer;
import indi.uhyils.protocol.mq.RabbitLogInfoConsumer;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JvmInfoAndLogRunner implements ApplicationRunner {


    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String exchangeName = RabbitMqContent.EXCHANGE_NAME;

        /* 第1个是启动JVM_START信息的 */
        MqUtil.addConsumer(exchangeName, RabbitMqContent.JVM_START_QUEUE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME, (channel) -> new RabbitJvmStartInfoConsumer(channel, applicationContext));

        /* 第2个是启动JVM_STATUS信息的 */
        MqUtil.addConsumer(exchangeName, RabbitMqContent.JVM_STATUS_QUEUE_NAME, RabbitMqContent.JVM_STATUS_QUEUE_NAME, (channel) -> new RabbitJvmStatusInfoConsumer(channel, applicationContext));

        /* 第3个是日志信息的(注,此queue流量巨大) */
        MqUtil.addNoLogConsumer(LogInfoSendMqUtil.getExchangeName(), LogInfoSendMqUtil.getQueueName(), LogInfoSendMqUtil
            .getQueueName(), (channel) -> new RabbitLogInfoConsumer(channel, applicationContext));
    }
}
