package indi.uhyils.core.topic;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时30分
 * @Version 1.0
 */
public abstract class AbstractTopic implements Topic {

    /**
     * 名称 topic确定了就不可变
     */
    protected final String name;

    /**
     * 推送类型
     */
    protected OutDealTypeEnum pushType;

    /**
     * 接收类型
     */
    protected OutDealTypeEnum receiveType;

    /**
     * 队列们
     */
    protected volatile Map<String, Queue> queues;

    protected AbstractTopic(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OutDealTypeEnum getPushType() {
        return pushType;
    }

    /**
     * 切换推送类型
     *
     * @param pushType
     */
    public abstract void setPushType(OutDealTypeEnum pushType);

    @Override
    public OutDealTypeEnum getReceiveType() {
        return receiveType;
    }

    /**
     * 切换接收消息类型
     *
     * @param receiveType
     */
    public abstract void setReceiveType(OutDealTypeEnum receiveType);

    public Map<String, Queue> getQueues() {
        return queues;
    }

    @Override
    public Boolean saveMessage(Message message) {
        initQueue();
        return saveMessage0(message);
    }

    /**
     * 保存消息
     *
     * @param message
     * @return
     */
    protected abstract Boolean saveMessage0(Message message);

    /**
     * 初始化队列
     */
    private void initQueue() {
        if (queues == null) {
            synchronized (this) {
                if (queues == null) {
                    this.queues = new HashMap<>();
                }
            }
        }
    }

    /**
     * 创建默认的队列
     *
     * @return
     */
    protected Queue createNewDefaultQueue() {
        Queue queue = QueueFactory.createNormalQueue(this);
        queues.put(Queue.DEFAULT_QUEUE, queue);
        return queue;
    }
}
