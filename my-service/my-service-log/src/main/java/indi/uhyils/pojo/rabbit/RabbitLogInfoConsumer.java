package indi.uhyils.pojo.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.factory.TraceInfoFactory;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.util.DefaultRequestBuildUtil;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.context.ApplicationContext;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月29日 14时14分
 */
public class RabbitLogInfoConsumer extends DefaultConsumer {

    private static final String THREAD_NAME = "log_deal_thread_";

    private Executor executor;

    private TraceInfoDao traceInfoDao;

    /**
     * @param channel
     * @param applicationContext
     */
    public RabbitLogInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        int process = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory());
        traceInfoDao = applicationContext.getBean(TraceInfoDao.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        try {
            executor.execute(() -> {
                try {
                    String text = new String(body, StandardCharsets.UTF_8);
                    text = text.substring(1, text.length() - 1);
                    TraceInfoEntity byText = TraceInfoFactory.createByText(text);
                    if (byText.getTraceId() != null && 1L == byText.getTraceId()) {
                        return;
                    }
                    if ((byText.getThreadName() != null && byText.getThreadName().startsWith(THREAD_NAME))) {
                        return;
                    }

                    byText.preInsert(DefaultRequestBuildUtil.getAdminDefaultRequest());
                    traceInfoDao.insert(byText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class LogDealThreadFactory implements ThreadFactory {

        private static final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + atomicInteger.getAndAdd(1));
        }
    }


}
