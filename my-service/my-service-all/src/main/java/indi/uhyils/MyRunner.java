package indi.uhyils;

import indi.uhyils.asm.TomcatURLStreamHandlerFactoryDump;
import indi.uhyils.load.MyUrlClassLoader;
import indi.uhyils.runner.MyRunnable;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月16日 13时59分
 */
public class MyRunner {

    /**
     * 每个项目线程的名称
     */
    private static final String THREAD_NAME = "sample";

    /**
     * app包路径
     */
    private static final String APP_SOURCE = "webapp";

    public static void main(String[] args) throws Exception {
        init(args);
    }

    private static void init(String[] args) throws Exception {
        ClassLoader classLoader = MyRunner.class.getClassLoader();
        URL plugins = classLoader.getResource(APP_SOURCE);

        Map<String, byte[]> classAsmCache = new HashMap<>(1);
        classAsmCache.put("org.apache.catalina.webresources.TomcatURLStreamHandlerFactory", TomcatURLStreamHandlerFactoryDump.dump());

        File pluginsFile = new File(plugins.getPath());
        int process = Runtime.getRuntime().availableProcessors();
        Executor executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory(), new CallerRunsPolicy());
        File[] pluginsDirs = pluginsFile.listFiles();
        Map<String, MyUrlClassLoader> map = new HashMap<>(pluginsDirs.length);
        for (File pluginDir : pluginsDirs) {
            File file = Objects.requireNonNull(pluginDir.listFiles())[0];
            MyUrlClassLoader myClassLoader = new MyUrlClassLoader(new URL[]{file.toURI().toURL()}, classAsmCache);
            map.put(pluginDir.getName(), myClassLoader);
        }

        for (MyUrlClassLoader value : map.values()) {
            executor.execute(new MyRunnable(value, args));
        }
    }

    private static class LogDealThreadFactory implements ThreadFactory {

        private static final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + atomicInteger.getAndAdd(1));
        }

    }
}
