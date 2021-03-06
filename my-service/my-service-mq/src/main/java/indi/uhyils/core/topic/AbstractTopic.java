package indi.uhyils.core.topic;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueFactory;
import indi.uhyils.core.register.Register;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import indi.uhyils.exception.ExpressionInvalidException;

import java.util.ArrayList;
import java.util.Collection;
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
    public Boolean saveMessage(Message message) throws ExpressionInvalidException {
        // 保证保存队列的地方被初始化了
        initQueue();
        return saveMessage0(message);
    }

    /**
     * 保存消息
     *
     * @param message
     * @return
     */
    protected abstract Boolean saveMessage0(Message message) throws ExpressionInvalidException;

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
    private Queue createNewDefaultQueue() throws ExpressionInvalidException {
        Queue queue = queueFactory.createNormalQueue(this);
        //新队列,尝试注册所有的消费者
        for (Register consumer : consumers) {
            queue.tryToRegister(consumer);
        }
        queues.put(Queue.DEFAULT_QUEUE, queue);
        return queue;
    }

    /**
     * 如果已经存在默认队列,则获取
     *
     * @return
     */
    protected Queue createOrGetDefaultQueue() throws ExpressionInvalidException {
        if (queues.containsKey(Queue.DEFAULT_QUEUE)) {
            return queues.get(Queue.DEFAULT_QUEUE);
        }
        return createNewDefaultQueue();
    }

    @Override
    public Boolean addNewRegister(final Register register) throws ExpressionInvalidException {
        if (register == null) {
            return Boolean.FALSE;
        }
        OutDealTypeEnum behaviorType = register.getBehaviorType();
        // 如果是发布订阅类的主题
        if (this instanceof PubSubTopic) {
            // 注: 主动推送,被动推送,主动拉取,被动拉取都属于topic的属性,每一个topic都存在这种属性
            if (register.getRegisterType() == RegisterType.PUBLISH && behaviorType == receiveType) {
                providers.add(register);
            } else if (register.getRegisterType() == RegisterType.SUBSCRIBER && behaviorType == pushType) {
                consumers.add(register);
                for (Queue t : getQueues().values()) {
                    t.tryToRegister(register);
                }
            } else {
                return Boolean.FALSE;
            }
        } else {
            if (register.getRegisterType() == RegisterType.PROVIDER && behaviorType == receiveType) {
                providers.add(register);
            } else if (register.getRegisterType() == RegisterType.COMSUMER && behaviorType == pushType) {
                consumers.add(register);
                for (Queue t : getQueues().values()) {
                    t.tryToRegister(register);
                }
            } else {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Message getMessage(String key) throws InterruptedException {
        key = key != null ? key : Queue.DEFAULT_QUEUE;
        Queue queue = queues.get(key);
        return queue.getOne();
    }
}
