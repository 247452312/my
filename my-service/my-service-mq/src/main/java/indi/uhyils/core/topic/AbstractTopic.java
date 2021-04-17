package indi.uhyils.core.topic;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueFactory;
import indi.uhyils.core.register.Register;

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

    /**
     * 接收者们
     */
    protected Collection<Register> providers = new ArrayList<>();
    /**
     * 推送者们
     */
    protected Collection<Register> consumers = new ArrayList<>();

    /**
     * 创建队列的工厂
     */
    private QueueFactory queueFactory;

    protected AbstractTopic(String name) {
        this.name = name;
    }

    public QueueFactory getQueueFactory() {
        return queueFactory;
    }

    @Override
    public void setQueueFactory(QueueFactory queueFactory) {
        this.queueFactory = queueFactory;
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
                    this.queues = new HashMap<>(16);
                }
            }
        }
    }

    /**
     * 创建默认的队列
     *
     * @return
     */
    private Queue createNewDefaultQueue() {
        Queue queue = queueFactory.createNormalQueue(this);
        queues.put(Queue.DEFAULT_QUEUE, queue);
        return queue;
    }

    /**
     * 如果已经存在默认队列,则获取
     *
     * @return
     */
    protected Queue createOrGetDefaultQueue() {
        if (queues.containsKey(Queue.DEFAULT_QUEUE)) {
            return queues.get(Queue.DEFAULT_QUEUE);
        }
        return createNewDefaultQueue();
    }

    @Override
    public Boolean addNewRegister(Register register) {
        if (register == null) {
            return false;
        }
        OutDealTypeEnum behaviorType = register.getBehaviorType();
        // 如果是发布订阅类的主题
        if (this instanceof PubSubTopic) {
            if (register.getRegisterType() == RegisterType.PUBLISH && behaviorType == receiveType) {
                providers.add(register);
            } else if (register.getRegisterType() == RegisterType.SUBSCRIBER && behaviorType == pushType) {
                consumers.add(register);
            } else {
                return false;
            }
        } else {
            if (register.getRegisterType() == RegisterType.PROVIDER && behaviorType == receiveType) {
                providers.add(register);
            } else if (register.getRegisterType() == RegisterType.COMSUMER && behaviorType == pushType) {
                consumers.add(register);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Collection<Register> getAllConsumer() {
        return consumers;
    }

    @Override
    public Boolean haveConsumer() {
        return consumers.size() != 0;
    }
}
