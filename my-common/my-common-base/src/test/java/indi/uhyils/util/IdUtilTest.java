package indi.uhyils.util;

import indi.uhyils.rpc.util.RpcAssertUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 18时44分
 */
public class IdUtilTest {

    private static final String THREAD_NAME = "log_deal_thread_";

    @Test
    public void newId() {

        IdUtil idUtil = new IdUtil();
        idUtil.setCode(1L);
        int process = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory(), new CallerRunsPolicy());
        Set<Long> set = Collections.synchronizedSet(new HashSet<>());
        int size = 100000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            executor.execute(() -> {
                long e = idUtil.newId();
                if (set.contains(e)) {
                    System.out.println("aaaaaaaaaaaaaaaaa");
                }
                set.add(e);
            });
        }
        executor.shutdown();
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println(useTime);
        RpcAssertUtil.assertTrue(set.size() == size, set.size() + " " + size);

    }

    private static class LogDealThreadFactory implements ThreadFactory {

        private static final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + atomicInteger.getAndAdd(1));
        }
    }

}
