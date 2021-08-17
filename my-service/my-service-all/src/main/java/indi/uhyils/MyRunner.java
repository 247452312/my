package indi.uhyils;

import indi.uhyils.load.MyClassLoader;
import indi.uhyils.util.LogUtil;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static void main(String[] args) {
        ClassLoader classLoader = MyRunner.class.getClassLoader();
        URL plugins = classLoader.getResource(APP_SOURCE);

        File file = new File(plugins.getPath());
        int process = Runtime.getRuntime().availableProcessors();
        Executor executor = new ThreadPoolExecutor(process, process * 2, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new LogDealThreadFactory(), new CallerRunsPolicy());
        File[] pluginsDirs = file.listFiles();
        Map<String, MyClassLoader> map = new HashMap<>();
        for (File pluginDir : pluginsDirs) {
            String name = pluginDir.getName();
            File jarFile = Objects.requireNonNull(pluginDir.listFiles())[0];
            MyClassLoader myClassLoader = MyClassLoader.newInstall(jarFile.getPath(), "indi.uhyils");
            map.put(name, myClassLoader);
        }
        for (MyClassLoader value : map.values()) {
            executor.execute(() -> {
                Class<?> myMainClass = null;
                try {
                    myMainClass = value.getMyMainClass();
                } catch (ClassNotFoundException e) {
                    LogUtil.error(e);
                }
                if (myMainClass == null) {
                    return;
                }
                try {
                    Method main = myMainClass.getDeclaredMethod("main", String[].class);
                    main.setAccessible(true);
                    Object[] obj = new Object[]{args};
                    main.invoke(myMainClass, obj);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    LogUtil.error(e);
                }
            });
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
