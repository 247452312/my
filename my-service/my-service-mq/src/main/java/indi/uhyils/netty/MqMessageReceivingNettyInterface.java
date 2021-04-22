package indi.uhyils.netty;

import org.springframework.context.ApplicationContextInitializer;

/**
 * mq的消息接收服务,各种协议的源头
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月22日 08时52分
 */
public interface MqMessageReceivingNettyInterface extends ApplicationContextInitializer {

    /**
     * 获取网络协议名称
     *
     * @return
     */
    String getProtocolName();

}
