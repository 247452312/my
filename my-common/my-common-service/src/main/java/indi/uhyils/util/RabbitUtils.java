package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import indi.uhyils.aop.TimeLogAop;
import indi.uhyils.content.RabbitMqContent;
import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;

import java.nio.charset.StandardCharsets;

/**
 * rabbitmq 工具集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 08时17分
 */
public class RabbitUtils {

    /**
     * 发送JVM start信息 并且发送第一次JVM状态信息
     *
     * @param channel       管道
     * @param jvmUniqueMark JVM唯一标示
     */
    public static void sendJvmStartInfo(Channel channel, JvmUniqueMark jvmUniqueMark) {
        if (channel == null) {
            return;
        }
        try {
            JvmStartInfo jvmStartInfo = JvmUtil.getJvmStartInfo(jvmUniqueMark);
            String s = JSON.toJSONString(jvmStartInfo);
            channel.basicPublish(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_START_QUEUE_NAME, null, s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LogUtil.error(RabbitUtils.class, e);
        }
    }

    /**
     * 发送JVM状态信息
     *
     * @param channel MQ管道
     */
    public static void sendJvmStatusInfo(Channel channel, JvmUniqueMark jvmUniqueMark) {
        if (channel == null) {
            return;
        }
        try {
            JvmStatusInfo jvmStatusInfo = JvmUtil.getJvmStatusInfo(jvmUniqueMark);
            String s = JSON.toJSONString(jvmStatusInfo);
            channel.basicPublish(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.JVM_STATUS_QUEUE_NAME, null, s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LogUtil.error(RabbitUtils.class, e);
        }

    }

    public static void sendInterfaceCallInfo(InterfaceCallInfo interfaceCallInfo, Channel channel) {
        if (!RabbitMqContent.getLogServiceOnLine()) {
            LogUtil.warn(RabbitUtils.class, "log微服务没有启动");
            return;
        }
        if (channel == null) {
            return;
        }
        try {
            LogUtil.info(RabbitUtils.class, "发送接口调用信息");
            String s = JSON.toJSONString(interfaceCallInfo);
            channel.basicPublish(RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.INTERFACE_CALL_INFO, null, s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LogUtil.error(TimeLogAop.class, e);
        }
    }
}
