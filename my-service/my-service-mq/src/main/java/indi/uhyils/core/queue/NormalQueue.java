package indi.uhyils.core.queue;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.topic.Topic;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时16分
 * @Version 1.0
 */
public class NormalQueue extends AbstractQueue {

    /**
     * 队列
     */
    private final LinkedBlockingQueue<Message> queue;

    public NormalQueue(Topic topic, Executor executor) {
        super(topic, executor);
        // 默认使用阻塞链表队列
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public Message getOne() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
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

    public java.util.Queue<Message> getQueue() {
        return queue;
    }
}
