package indi.uhyils.core.queue;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.topic.Topic;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时16分
 * @Version 1.0
 */
public class NormalQueue implements Queue {

    /**
     * 主题
     */
    private Topic topic;

    /**
     * 队列
     */
    private java.util.Queue<Message> queue;

    /**
     * 此队列使用的线程池
     */
    private Executor executor;

    public NormalQueue(Topic topic, Executor executor) {
        this.topic = topic;
        // 默认使用阻塞链表队列
        this.queue = new LinkedBlockingQueue<>();
        this.executor = executor;
    }

    @Override
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public Object getOne() {
        if (queue.size() != 0) {
            return queue.poll();
        } else {
            return null;
        }
    }

    @Override
    public Object[] getMany(Integer count) {
        Object[] result = new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = queue.poll();
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
        return queue.add(message);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public Executor getExecutor() {
        return this.executor;
    }

    @Override
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public java.util.Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(java.util.Queue<Message> queue) {
        this.queue = queue;
    }
}
