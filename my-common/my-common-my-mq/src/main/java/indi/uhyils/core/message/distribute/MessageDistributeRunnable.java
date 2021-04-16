package indi.uhyils.core.message.distribute;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueObserver;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.topic.Topic;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息分发模板,只有主动的队列才会开启此模板
 * 
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 20时55分
 * @Version 1.0
 */
public abstract class MessageDistributeRunnable extends Thread implements QueueObserver {

    /**
     * 队列
     */
    private final Queue queue;
    private static AtomicInteger integer = new AtomicInteger(0);

    public MessageDistributeRunnable(Queue queue) {
        super("message_distribute_" + integer.addAndGet(1));
        this.queue = queue;
    }

    @Override
    public Queue getQueue() {
        return queue;
    }

    @Override
    public void run() {
        // 线程可打断
        while (!isInterrupted()) {
            Message one;
            try {
                Topic topic = queue.getTopic();
                // 过滤掉没有接受者的情况
                if (!topic.haveConsumer()) {
                    Thread.sleep(1000);
                    continue;
                }
                while ((!isInterrupted() && (one = getQueue().getOne()) != null)) {
                    sendMessage(one, topic.getAllConsumer());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /**
     * 分发消息,由每个模板实现
     * 
     * @param message
     * @param registers
     * @throws Throwable
     */
    public abstract void sendMessage(Message message, Collection<Register> registers) throws Throwable;
}
