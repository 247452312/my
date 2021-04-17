package indi.uhyils.core.topic;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import java.util.Collection;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.QueueFactory;
import indi.uhyils.core.register.Register;

/**
 * 主题,一个MQ中存在多个主题,每个主题有可能是不同的类型,也有可能是相同的类型
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 16时04分
 * @Version 1.0
 */
public interface Topic {
    /**
     * 获取topic名称
     *
     * @return
     */
    String getName();

    /**
     * 获取类型
     *
     * @return
     */
    TopicType getTopicType();

    /**
     * 获取消费类型
     *
     * @return
     */
    OutDealTypeEnum getPushType();

    /**
     * 获取消息接收类型
     *
     * @return
     */
    OutDealTypeEnum getReceiveType();

    /**
     * 保存消息
     *
     * @param message
     *            消息
     * @return
     */
    Boolean saveMessage(Message message);

    /**
     * 添加新的注册者到这个topic上
     *
     * @param register
     * @return
     */
    Boolean addNewRegister(Register register);

    /**
     * 设置队列工厂
     *
     * @param factory
     * @return
     */
    void setQueueFactory(QueueFactory factory);

    /**
     * 获取所有的
     * 
     * @return
     */
    Collection<Register> getAllConsumer();

    /**
     * 是否存在consumer
     * 
     * @return
     */
    Boolean haveConsumer();
}
