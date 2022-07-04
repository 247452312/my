package indi.uhyils;

import indi.uhyils.context.TempContext;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月28日 14时18分
 */
class MyExecutorWrapperTest {

    @org.junit.jupiter.api.Test
    void execute() throws InterruptedException {

        MyExecutorWrapper wrapper = MyExecutorWrapper.createByThreadPoolExecutor(new ThreadPoolExecutor(1, 1, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100)));
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 0) {
                TempContext.temp.set("asd" + i);
            } else {
                TempContext.temp.remove();
            }
            wrapper.execute(() -> {
                final String s = TempContext.temp.get();
                System.out.println(s);
            });
            Thread.sleep(10L);
        }
    }

    @org.junit.jupiter.api.Test
    void execute2() throws InterruptedException {

        MyExecutorWrapper wrapper = MyExecutorWrapper.createByThreadPoolExecutor(new ThreadPoolExecutor(1, 1, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100)));
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 0) {
                TempContext.temp2.set("asd" + i);
            } else {
                TempContext.temp2.remove();
            }
            wrapper.execute(() -> {
                final String s = TempContext.temp2.get();
                System.out.println(s);
            });
            Thread.sleep(10L);
        }
    }
}
