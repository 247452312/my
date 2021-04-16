package indi.uhyils.core.queue;

import indi.uhyils.core.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 队列工厂
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时13分
 * @Version 1.0
 */
@Component
public class QueueFactory {

    /**
     * 队列使用的线程池
     */
    protected Executor queueExecutor;
    @Autowired
    private QueueFactoryConfig config;

    public QueueFactory() {
    }

    @PostConstruct
    public void init() {
        queueExecutor = new ThreadPoolExecutor(config.coreSize, config.maxSize, config.alive, config.timeUnit, new ArrayBlockingQueue<>(300), new QueueThreadFactory());
    }


    public Queue createNormalQueue(Topic topic) {
        return new NormalQueue(topic, queueExecutor);
    }

    private class QueueThreadFactory implements ThreadFactory {
        /**
         * thread prefix
         */
        private static final String QUEUE_PREFIX_NAME = "queue_thread_";

        private final AtomicInteger q = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, QUEUE_PREFIX_NAME + q.addAndGet(1));
        }
    }

}
