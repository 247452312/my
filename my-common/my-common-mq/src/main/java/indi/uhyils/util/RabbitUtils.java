package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import indi.uhyils.aop.InterfaceCellAop;
import indi.uhyils.content.RabbitMqContent;
import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;

import java.nio.charset.StandardCharsets;

/**
 * rabbitmq 工具集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 08时17分
 */
public class RabbitUtils {



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
            LogUtil.error(InterfaceCellAop.class, e);
        }
    }
}
