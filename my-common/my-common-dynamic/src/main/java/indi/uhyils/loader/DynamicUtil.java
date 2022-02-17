package indi.uhyils.loader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月16日 10时24分
 */
public final class DynamicUtil {

    private DynamicUtil() {
        throw new RuntimeException();
    }


    /**
     * 初始化动态代码classLoader
     */
    public static void initDynamicClassLoader(String[] args) throws InterruptedException {
        if (Thread.currentThread().getContextClassLoader().getClass().getName().equals(DynamicRunnerClassLoader.class.getName())) {
            return;
        }
        String mainClassName = getMainClassName();
        IsolatedThreadGroup threadGroup = new IsolatedThreadGroup(mainClassName);
        Thread thread = new Thread(threadGroup, new MyDynamicLaunchRunner(mainClassName, args), "main");
        thread.setContextClassLoader(new DynamicRunnerClassLoader());
        thread.start();
        join(threadGroup);
        threadGroup.rethrowUncaughtException();
    }

    public static void join(ThreadGroup threadGroup) {
        boolean hasNonDaemonThreads;
        do {
            hasNonDaemonThreads = false;
            Thread[] threads = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threads);
            int length = threads.length;

            for (int i = 0; i < length; ++i) {
                Thread thread = threads[i];
                if (thread != null && !thread.isDaemon()) {
                    try {
                        hasNonDaemonThreads = true;
                        thread.join();
                    } catch (InterruptedException var8) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } while (hasNonDaemonThreads);

    }

    /**
     * 获取启动类名称,注意, 只在主线程中有效
     *
     * @return
     */
    private static String getMainClassName() {
        StackTraceElement[] stackTrace = (new RuntimeException()).getStackTrace();
        int length = stackTrace.length;
        for (int i = 0; i < length; ++i) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if ("main".equals(stackTraceElement.getMethodName())) {
                return stackTraceElement.getClassName();
            }
        }
        return null;
    }

}
