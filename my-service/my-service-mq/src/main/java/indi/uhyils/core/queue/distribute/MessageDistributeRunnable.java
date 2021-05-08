package indi.uhyils.core.queue.distribute;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueObserver;
import indi.uhyils.core.register.Register;
import indi.uhyils.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息分发模板,只有主动的队列才会开启此模板,这里只是控制发送的行为逻辑.并不是发送本身,也就是说这里控制谁可以发送
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 20时55分
 * @Version 1.0
 */
public abstract class MessageDistributeRunnable extends Thread implements QueueObserver {

    private static AtomicInteger integer = new AtomicInteger(0);
    /**
     * 此分发者负责的消息接受者
     */
    private final List<Register> consumer;
    /**
     * 队列
     */
    private final Queue queue;

    public MessageDistributeRunnable(Queue queue) {
        super("message_distribute_" + integer.addAndGet(1));
        this.queue = queue;
        this.consumer = queue.getConsumer();
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
                // 过滤掉没有接受者的情况
                if (CollectionUtils.isEmpty(consumer)) {
                    Thread.sleep(1000);
                    continue;
                }
                while ((!isInterrupted() && (one = getQueue().takeOne()) != null)) {
                    sendMessage(one, consumer);
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
