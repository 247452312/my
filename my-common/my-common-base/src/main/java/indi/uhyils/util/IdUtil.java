package indi.uhyils.util;

import indi.uhyils.content.Content;
import indi.uhyils.exception.IdGenerationException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 本项目id生产规则
 * <p>
 * long类型共计8字节64位,最高位恒为0代表正数,第1位到第43位存储时间戳,44-54位为10位序列数(防重),第44位到底48位(5位)代表分布式节点index,59-64位预留,等待其他业务需要
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月06日 19时27分
 */
@Component
public class IdUtil {

    // todo 这个code在RPC初始化时需要从nacos中获取
    @Value("${id.organization.code:-1}")
    private Long code;

    /**
     * 存储上一次生成的时间,保证系统时间不正确时不产生错误的id
     */
    private volatile Long lastTime = 0L;

    /**
     * 序列号
     */
    private volatile AtomicLong sequence = new AtomicLong(0L);

    public void setCode(Long code) {
        this.code = code;
    }

    public synchronized long newId() throws IdGenerationException {
        // 生成时间
        long time = System.currentTimeMillis();
        if (lastTime > time) {
            throw new IdGenerationException("系统时间不正确");
        }
        if (lastTime != time) {
            // 如果不是之前的毫秒,则sequence归零,继续生成序列号
            sequence.set(0L);
            lastTime = time;
        }
        // 获取序列号
        long sq = sequence.getAndIncrement();

        // 如果序列号超出,则阻塞到下一个毫秒继续获取序列号
        if (sq > Content.SEQUENCE_MASK) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                LogUtil.error(e);
                Thread.currentThread().interrupt();
            }
            return newId();
        }
        // 从配置文件中获取 代表学校码
        long distributedResult = (code & Content.DISTRIBUTED_MASK) << Content.DISTRIBUTED_DISPLACEMENT;

        //时间戳
        long timeResult = (time & Content.TIME_MASK) << Content.TIME_DISPLACEMENT;

        // 序列数
        long sqResult = (sq & Content.SEQUENCE_MASK) << Content.SEQUENCE_DISPLACEMENT;

        return timeResult | sqResult | distributedResult;
    }
}
