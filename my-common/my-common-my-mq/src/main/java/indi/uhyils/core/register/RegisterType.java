package indi.uhyils.core.register;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 22时26分
 * @Version 1.0
 */
public enum RegisterType {
    /**
     * 消息生产者
     */
    PROVIDER,
    /**
     * 消息消费者
     */
    COMSUMER,
    /**
     * 消息发布者
     */
    PUBLISH,
    /**
     * 消息订阅者
     */
    SUBSCRIBER;
}
