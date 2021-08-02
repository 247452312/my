package indi.uhyils.mq.scheduled;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.ConfirmListener;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.mq.util.JvmUtil;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.util.LogUtil;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
            JvmStartInfo jvmStartInfo = JvmUtil.getJvmStartInfo(jvmUniqueMark);
            MqUtil.sendConfirmMsg(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME, new ConfirmListener() {

                /**
                 * 消息处理成功
                 * @param deliveryTag 唯一标示
                 * @param multiple 未知
                 */
                @Override
                public void handleAck(long deliveryTag, boolean multiple) {
                    synchronized (this) {
                        LogUtil.info(this, "JVM启动消息处理成功(定时任务)");
                        // 设置interface可以开始干活了
                        RabbitMqContent.setLogServiceOnLine(Boolean.TRUE);
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
            }, JSON.toJSONString(jvmStartInfo));
        } else {

            JvmStatusInfo jvmStatusInfo = JvmUtil.getJvmStatusInfo(jvmUniqueMark);
            // 否则正常发送
            MqUtil.sendConfirmMsg(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_STATUS_QUEUE_NAME, new ConfirmListener() {

                @Override
                public void handleAck(long deliveryTag, boolean multiple) {
                    LogUtil.info(this, "JVM状态消息处理成功");
                    // 成功了就开启interface发送
                    RabbitMqContent.setLogServiceOnLine(Boolean.TRUE);

                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) {
                    // 失败了就取消interface发送
                    RabbitMqContent.setLogServiceOnLine(Boolean.FALSE);
                    LogUtil.warn(this, "JVM信息处理失败");
                }
            }, JSON.toJSONString(jvmStatusInfo));
        }
    }

    public JvmUniqueMark getJvmUniqueMark() {
        return jvmUniqueMark;
    }

}
