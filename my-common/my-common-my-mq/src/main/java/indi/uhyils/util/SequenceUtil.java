package indi.uhyils.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 20时34分
 * @Version 1.0
 */
public class SequenceUtil {

    /**
     * 存储上一次生成的时间,保证系统时间不正确时不产生错误的id
     */
    private static volatile Long lastTime = 0L;

    /**
     * 序列号
     */
    private static AtomicLong sequence = new AtomicLong(0L);

    public static long newSequence() throws SequenceGenerationException, InterruptedException {
        // 生成时间
        long time = System.currentTimeMillis();
        if (lastTime > time) {
            throw new SequenceGenerationException("系统时间不正确");
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
            Thread.sleep(1L);
            return newSequence();
        }
        //时间戳
        long timeResult = (time & Content.TIME_MASK) << Content.TIME_DISPLACEMENT;
        // 序列数
        long sqResult = (sq & Content.SEQUENCE_MASK) << Content.SEQUENCE_DISPLACEMENT;
        return timeResult | sqResult;
    }
}
