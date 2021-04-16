package indi.uhyils.core.queue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 09时32分
 */
@Component
@ConfigurationProperties(prefix = "queue")
public class QueueFactoryConfig {

    /**
     * 核心线程数(不关闭的线程)
     */
    public Integer coreSize;

    /**
     * 最大线程数
     */
    public Integer maxSize;

    /**
     * 非核心线程空闲存活时间
     */
    public Integer alive;

    /**
     * 时间单位
     */
    public TimeUnit timeUnit = TimeUnit.MINUTES;


}
