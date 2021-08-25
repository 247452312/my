package indi.uhyils.pojo.rabbit;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.dao.TraceLogDao;
import indi.uhyils.enum_.LogDetailTypeEnum;
import indi.uhyils.trace.DetailTraceDeal;
import indi.uhyils.trace.LinkTraceDeal;
import indi.uhyils.trace.LogTraceDeal;
import indi.uhyils.trace.TraceDealInterface;
import indi.uhyils.util.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
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

    private TraceDetailDao traceDetailDao;

    private TraceLogDao traceLogDao;

    /**
     * @param channel
     * @param applicationContext
     */
    public RabbitLogInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        int process = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory(), new CallerRunsPolicy());
        traceInfoDao = applicationContext.getBean(TraceInfoDao.class);
        traceDetailDao = applicationContext.getBean(TraceDetailDao.class);
        traceLogDao = applicationContext.getBean(TraceLogDao.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        executor.execute(() -> {
            String text = new String(body, StandardCharsets.UTF_8);
            try {
                text = text.substring(1, text.length() - 1);
                LogDetailTypeEnum parse = LogDetailTypeEnum.parse(text.charAt(0));
                TraceDealInterface traceDeal = null;
                switch (parse) {
                    case DETAIL:
                        traceDeal = new DetailTraceDeal();
                        traceDeal.init(traceDetailDao);
                        break;
                    case LOG:
                        traceDeal = new LogTraceDeal();
                        traceDeal.init(traceLogDao);
                        break;
                    case LINK:
                        traceDeal = new LinkTraceDeal();
                        traceDeal.init(traceInfoDao);
                        break;
                    default:
                        throw new RuntimeException("前缀错误" + text.charAt(0));
                }
                traceDeal.doDeal(text);
            } catch (Exception e) {
                LogUtil.error(e, text);
            }
        });


    }

    private static class LogDealThreadFactory implements ThreadFactory {

        private static final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + atomicInteger.getAndAdd(1));
        }
    }


}
