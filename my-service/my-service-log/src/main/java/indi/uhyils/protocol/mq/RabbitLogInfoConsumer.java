package indi.uhyils.protocol.mq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.enum_.LogDetailTypeEnum;
import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.TraceLogDTO;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.trace.DetailTraceDeal;
import indi.uhyils.pojo.trace.LinkTraceDeal;
import indi.uhyils.pojo.trace.LogTraceDeal;
import indi.uhyils.service.TraceDetailService;
import indi.uhyils.service.TraceInfoService;
import indi.uhyils.service.TraceLogService;
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

    private static final String THREAD_NAME = "thread_";

    /**
     * 包含这个字段的日志不发送MQ
     */
    private static final String TRACE_INFO = "sys_trace";

    private final Executor executor;

    private final TraceDetailService traceDetailService;

    private final TraceInfoService traceInfoService;

    private final TraceLogService traceLogService;

    /**
     * @param channel
     * @param applicationContext
     */
    public RabbitLogInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        int process = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory(), new CallerRunsPolicy());
        traceDetailService = applicationContext.getBean(TraceDetailService.class);
        traceInfoService = applicationContext.getBean(TraceInfoService.class);
        traceLogService = applicationContext.getBean(TraceLogService.class);
    }

    /**
     * 注: 此处不打印日志,如果打印日志,会造成递归效果.快速将磁盘吃没 具体方法为,线程名称中带有:
     * {@link RabbitLogInfoConsumer#TRACE_INFO}
     * 请不要在此方法创建的线程中再次创建其他线程
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        executor.execute(() -> {
            String text = new String(body, StandardCharsets.UTF_8);
            try {
                text = text.substring(1, text.length() - 1);
                LogDetailTypeEnum parse = LogDetailTypeEnum.parse(text.charAt(0));
                switch (parse) {
                    case DETAIL:
                        TraceDetailDTO traceDetailDO = new DetailTraceDeal().doDeal(text);
                        AddCommand<TraceDetailDTO> addCommand = new AddCommand<>();
                        addCommand.setDto(traceDetailDO);
                        traceDetailService.add(addCommand);
                        break;
                    case LOG:
                        TraceLogDTO traceDeal = new LogTraceDeal().doDeal(text);
                        AddCommand<TraceLogDTO> add = new AddCommand<>();
                        add.setDto(traceDeal);
                        traceLogService.add(add);
                        break;
                    case LINK:
                        TraceInfoDTO traceInfoDTO = new LinkTraceDeal().doDeal(text);
                        AddCommand<TraceInfoDTO> traceInfoDTOAddCommand = new AddCommand<>();
                        traceInfoDTOAddCommand.setDto(traceInfoDTO);
                        traceInfoService.add(traceInfoDTOAddCommand);
                        break;
                    default:
                        LogUtil.error("前缀错误" + text.charAt(0));
                }
            } catch (Exception e) {
                LogUtil.error(e, text);
            }
        });


    }

    private static class LogDealThreadFactory implements ThreadFactory {

        private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, TRACE_INFO + "_" + THREAD_NAME + ATOMIC_INTEGER.getAndAdd(1));
        }
    }


}
