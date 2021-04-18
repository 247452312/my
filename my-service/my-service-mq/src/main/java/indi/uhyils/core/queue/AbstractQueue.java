package indi.uhyils.core.queue;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.distribute.MessageDistributeRunnable;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.topic.Topic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 20时50分
 * @Version 1.0
 */
public abstract class AbstractQueue implements Queue {

    /**
     * 队列
     */
    private final LinkedBlockingQueue<Message> queue;
    /**
     * 主题
     */
    protected Topic topic;
    /**
     * 所有队列共用的线程池
     */
    protected Executor executor;
    /**
     * 符合此队列的消息消费者
     */
    protected List<Register> consumer;
    /**
     * 此队列的消息分发者
     */
    protected MessageDistributeRunnable distribute;

    public AbstractQueue(Topic topic, Executor executor) {
        this.topic = topic;
        this.executor = executor;
        // 默认使用阻塞链表队列
        this.queue = new LinkedBlockingQueue<>();
        consumer = new ArrayList<>();
        initDistribute();
        // 这里存在this指针溢出的问题
        if (distribute != null) {
            executor.execute(distribute);
        }
    }

    @Override
    public Message takeOne() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Message getOne() throws InterruptedException {
        return queue.poll(1L, TimeUnit.MILLISECONDS);
    }

    @Override
    public Message[] getMany(Integer count) {
        Message[] result = new Message[count];
        try {
            for (int i = 0; i < count; i++) {
                result[i] = queue.poll(3L, TimeUnit.MINUTES);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long getMaxSequence() {
        Optional<Message> min = queue.stream().max(Comparator.comparing(Message::getSequence));
        return min.map(Message::getSequence).orElse(null);
    }

    @Override
    public Long getMinSequence() {
        Optional<Message> min = queue.stream().min(Comparator.comparing(Message::getSequence));
        return min.map(Message::getSequence).orElse(null);
    }

    @Override
    public Boolean saveMessage(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return queue.size();
    }

    /**
     * 初始化队列的消息分发者
     */
    protected abstract void initDistribute();

    @Override
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public List<Register> getConsumer() {
        return this.consumer;
    }
}
