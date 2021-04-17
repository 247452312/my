package indi.uhyils.core.queue;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import java.util.concurrent.Executor;

import indi.uhyils.core.message.distribute.AllPushMessageDistribute;
import indi.uhyils.core.message.distribute.MessageDistributeRunnable;
import indi.uhyils.core.message.distribute.OneMessageDistribute;
import indi.uhyils.core.topic.Topic;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 20时50分
 * @Version 1.0
 */
public abstract class AbstractQueue implements Queue {

    /**
     * 主题
     */
    private Topic topic;

    /**
     * 此队列使用的线程池
     */
    private Executor executor;

    /**
     * 分发器
     */
    private MessageDistributeRunnable distribute;

    public AbstractQueue(Topic topic, Executor executor) {
        this.topic = topic;
        this.executor = executor;
        if (topic.getPushType() == OutDealTypeEnum.ACTIVE) {
            if (topic.getTopicType() == TopicType.PUB_SUB) {
                this.distribute = new AllPushMessageDistribute(this);
            } else {
                this.distribute = new OneMessageDistribute(this);
            }
        }
    }

    @Override
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public Boolean startDistributeThread() {
        if (distribute != null) {
            executor.execute(distribute);
        }
        return true;
    }

}
