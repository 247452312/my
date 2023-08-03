package indi.uhyils.redis;

import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月16日 09时16分
 */

public class RedisLockTest {

    private RedisPool redisPool;

    @BeforeEach
    public void setUp() throws Exception {
        redisPool = new RedisPool();
        Class<? extends RedisPool> clazz = redisPool.getClass();
        Field redisIp = clazz.getDeclaredField("redisIp");
        redisIp.setAccessible(true);
        redisIp.set(redisPool, "prod");

        Field redisPort = clazz.getDeclaredField("redisPort");
        redisPort.setAccessible(true);
        redisPort.set(redisPool, "6379");

        Field password = clazz.getDeclaredField("password");
        password.setAccessible(true);
        password.set(redisPool, "uhyils");

        Method initPool = clazz.getDeclaredMethod("initPool");
        initPool.setAccessible(true);
        Object invoke = initPool.invoke(redisPool);
        Asserts.assertTrue((Boolean) invoke);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void getLockName() {
        String abc = "abc";
        RedisLock redisLock = new RedisLock(abc, redisPool, "test");
        String lockName = redisLock.getLockName();
        Asserts.assertTrue(Objects.equals(abc, lockName));
    }

    @Test
    public void lock() throws Exception {
        int threadCount = 10;
        AtomicInteger integer = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(threadCount);
        Map<Integer, Integer> map = new HashMap<>(threadCount);
        List<Callable<Boolean>> callables = new ArrayList<>(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int index = i;
            Callable<Boolean> test = () -> {
                latch.countDown();
                LogUtil.info(this, "线程" + index + " 就位");
                latch.await();
                RedisLock redisLock = new RedisLock("test", redisPool, "test" + index);
                try {
                    boolean lock = false;
                    while (!lock) {
                        lock = redisLock.lock(20L);
                    }
                    try {
                        LogUtil.info(this, "start" + index);
                        int start = integer.getAndAdd(1);
                        Thread.sleep(10L);
                        int end = integer.getAndAdd(1);
                        Asserts.assertTrue(!map.containsKey(start));
                        map.put(start, end);
                        LogUtil.info(this, "end" + index);
                        return true;
                    } finally {
                        redisLock.unlock();
                    }
                } catch (Throwable e) {
                    LogUtil.error(this, e);
                    return false;
                }
            };
            callables.add(test);
        }
        List<Future<Boolean>> collect = callables.stream().map(executor::submit).collect(Collectors.toList());
        for (Future<Boolean> callable : collect) {
            Asserts.assertTrue(callable.get());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Asserts.assertTrue(entry.getKey() + 1 == entry.getValue());
        }
    }


    @Test
    public void countDown() throws RedisCountDownLatchExistsException, InterruptedException {
        RedisLock redisLock = new RedisLock("testk", redisPool, "testk");
        int count = 10;
        String uniqueName = "test";

        ExecutorService executor = Executors.newFixedThreadPool(count);

        boolean test = redisLock.createCountDownLatch(uniqueName, count);
        List<Future<Boolean>> list = new ArrayList<>(count);
        if (test) {
            for (int i = 0; i < count - 1; i++) {
                Callable<Boolean> callable = () -> redisLock.countDown(uniqueName);
                Future<Boolean> submit = executor.submit(callable);
                list.add(submit);
            }
        }
        boolean b = redisLock.countDown(uniqueName);
        Asserts.assertTrue(b);
    }
}
