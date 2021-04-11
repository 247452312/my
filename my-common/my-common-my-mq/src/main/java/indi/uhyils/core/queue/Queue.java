package indi.uhyils.core.queue;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.topic.Topic;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 16时22分
 * @Version 1.0
 */
public interface Queue {

    /**
     * 默认队列名称
     */
    String DEFAULT_QUEUE = "defaultQueue";


    /**
     * 获取队列所在topic
     *
     * @return
     */
    Topic getTopic();

    /**
     * 获取一个,如果是普通消息,则获取第一个 如果是全局顺序消息,则通知topic 获取全局第一个, 如果是分区顺序消息,则通知topic获取此区第一个
     *
     * @return
     */
    Object getOne();

    /**
     * 获取n个
     *
     * @param count
     * @return
     */
    Object[] getMany(Integer count);

    /**
     * 获取此队列中的最大序列(最晚的一个)
     *
     * @return
     */
    Long getMaxSequence();

    /**
     * 获取此队列中最短序列(最早的一个)
     *
     * @return
     */
    Long getMinSequence();

    /**
     * 保存信息
     *
     * @param message
     * @return
     */
    Boolean saveMessage(Message message);


}
