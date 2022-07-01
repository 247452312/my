package indi.uhyils;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * executor包装器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月27日 15时03分
 */
public class MyExecutorWrapper extends ThreadPoolExecutor {


    private final Executor executor;

    private MyExecutorWrapper(ThreadPoolExecutor executor) {
        super(executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getKeepAliveTime(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS, executor.getQueue(), executor.getThreadFactory(), executor.getRejectedExecutionHandler());
        this.executor = executor;
    }

    /**
     * 创建一个新的executor
     *
     * @param executor
     *
     * @return
     */
    public static MyExecutorWrapper createByThreadPoolExecutor(ThreadPoolExecutor executor) {
        return new MyExecutorWrapper(executor);
    }

    @Override
    public void execute(Runnable command) {
        this.executor.execute(wrapperRunnable(command));
    }

    /**
     * 包装
     *
     * @param command
     *
     * @return
     */
    private Runnable wrapperRunnable(Runnable command) {
        return this.new MyRunnableWrapper(command);
    }

    /**
     * runnable包装类
     */
    class MyRunnableWrapper implements Runnable {

        private final AtomicReference<Map<MyThreadLocal<?>, Object>> tempSaveThreadLocal = new AtomicReference<>(MyThreadLocal.copy());

        private final Runnable runnable;

        private MyRunnableWrapper(Runnable runnable) {
            this.runnable = runnable;
        }


        @Override
        public void run() {
            MyThreadLocal.initChildThreadLocal(tempSaveThreadLocal.get());
            try {
                runnable.run();
            } finally {
                MyThreadLocal.removeChildThreadLocal();
                tempSaveThreadLocal.set(null);
            }
        }
    }
}
