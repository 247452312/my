package indi.uhyils.loader;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import sun.misc.Unsafe;

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
    public static void initDynamicClassLoader(String[] args) {
        if (Thread.currentThread().getContextClassLoader().getClass().getName().equals(DynamicRunnerClassLoader.class.getName())) {
            return;
        }

        URL[] urls = getUrls(DynamicUtil.class.getClassLoader());
        if (urls == null) {
            throw new IllegalStateException("Can not find urls from the ClassLoader of PandoraBootstrap. ClassLoader: " + DynamicUtil.class.getClassLoader());
        }
        DynamicRunnerClassLoader cl = new DynamicRunnerClassLoader(urls, ClassLoader.getSystemClassLoader());
        Thread.currentThread().setContextClassLoader(cl);

        String mainClassName = getMainClassName();
        IsolatedThreadGroup threadGroup = new IsolatedThreadGroup(mainClassName);
        Thread thread = new Thread(threadGroup, new MyDynamicLaunchRunner(mainClassName, args), "main");
        thread.setContextClassLoader(cl);
        thread.start();
        join(threadGroup);
        threadGroup.rethrowUncaughtException();
        System.exit(0);
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


    public static URL[] getUrls(ClassLoader classLoader) {
        if (classLoader instanceof URLClassLoader) {
            return ((URLClassLoader) classLoader).getURLs();
        } else if (classLoader.getClass().getName().startsWith("jdk.internal.loader.ClassLoaders$")) {
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                Unsafe unsafe = (Unsafe) field.get((Object) null);
                Field ucpField = classLoader.getClass().getDeclaredField("ucp");
                long ucpFieldOffset = unsafe.objectFieldOffset(ucpField);
                Object ucpObject = unsafe.getObject(classLoader, ucpFieldOffset);
                Field pathField = ucpField.getType().getDeclaredField("path");
                long pathFieldOffset = unsafe.objectFieldOffset(pathField);
                ArrayList<URL> path = (ArrayList) unsafe.getObject(ucpObject, pathFieldOffset);
                return (URL[]) path.toArray(new URL[path.size()]);
            } catch (Exception var11) {
                var11.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
