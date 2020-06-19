package indi.uhyils.runner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import indi.uhyils.content.RabbitMqContent;
import indi.uhyils.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.rabbit.RabbitFactory;
import indi.uhyils.scheduled.RabbitMqTask;
import indi.uhyils.util.JvmUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.RabbitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * JVM启动时要发送一条jvm启动的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 09时58分
 */
@Component
@Order(1)
public class JvmStartInfoSendRunner implements ApplicationRunner {

    @Autowired
    private JvmUniqueMark jvmUniqueMark;

    @Autowired
    private RabbitMqTask rabbitMqTask;

    @Autowired
    private RabbitFactory rabbitFactory;

    private Channel channel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO 这里为什么不改成定时任务启动自动执行一次?
        synchronized (rabbitMqTask.getStartInfoSended()) {
            if (!rabbitMqTask.getStartInfoSended()) {
                if (channel == null) {
                    channel = rabbitFactory.getConn().createChannel();
                    channel.confirmSelect();
                    //创建exchange
                    channel.exchangeDeclare(RabbitMqContent.EXCHANGE_NAME, "direct", false, false, null);
                    //创建队列
                    channel.queueDeclare(RabbitMqContent.JVM_START_QUEUE_NAME, false, false, false, null);
                    //绑定exchange和queue
                    channel.queueBind(RabbitMqContent.JVM_START_QUEUE_NAME, RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME);

                    channel.addConfirmListener(new ConfirmListener() {
                        /**
                         * 消息处理成功
                         * @param deliveryTag
                         * @param multiple
                         * @throws IOException
                         */
                        @Override
                        public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                            synchronized (rabbitMqTask) {
                                LogUtil.info(this, "JVM启动消息处理成功(Runner)");
                                // 设置唯一标示
                                rabbitMqTask.setJvmUniqueMark(jvmUniqueMark);
                                // 设置起始信息已经发送过
                                rabbitMqTask.setStartInfoSended(true);
                                // 设置interface可以开始干活了
                                RabbitMqContent.setLogServiceOnLine(true);
                                // 设置为空 释放内存
                                JvmStartInfo.setStatusInfos(null);
                            }

                        }

                        /**
                         * 消息处理失败
                         * @param deliveryTag
                         * @param multiple
                         * @throws IOException
                         */
                        @Override
                        public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                            LogUtil.warn(this, "JVM启动消息处理失败(Runner)");
                        }
                    });
                }
                RabbitUtils.sendJvmStartInfo(channel, jvmUniqueMark);
            }
        }
    }
}
