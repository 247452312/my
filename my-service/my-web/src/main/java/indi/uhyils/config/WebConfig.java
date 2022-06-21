package indi.uhyils.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月03日 20时44分
 */
@Configuration
public class WebConfig {


    /**
     * 审核线程池的对象
     *
     * @return
     */
    @Bean("actionExecutor")
    public ThreadPoolTaskExecutor getActionExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int process = Runtime.getRuntime().availableProcessors();
        // 配置核心线程数
        executor.setCorePoolSize(process);
        // 配置最大线程数
        executor.setMaxPoolSize(process * 2);
        // 配置队列大小
        executor.setQueueCapacity(20000);
        // 等待任务在关机时完成--表明等待所有线程执行完
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        executor.setAwaitTerminationSeconds(120);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("action-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
