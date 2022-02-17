package indi.uhyils.loader;

import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月17日 21时45分
 */
public class MyDynamicLaunchRunner implements Runnable {

    private final String mainClassName;

    private final String[] args;

    public MyDynamicLaunchRunner(String mainClassName, String... args) {
        this.mainClassName = mainClassName;
        this.args = args != null ? args : new String[0];
    }


    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        ClassLoader classLoader = thread.getContextClassLoader();

        try {
            Class<?> startClass = classLoader.loadClass(this.mainClassName);
            Method mainMethod = startClass.getMethod("main", String[].class);
            if (!mainMethod.isAccessible()) {
                mainMethod.setAccessible(true);
            }

            mainMethod.invoke(null, new Object[]{this.args});
        } catch (NoSuchMethodException var10) {
            Exception wrappedEx = new Exception("The specified mainClass doesn't contain a main method with appropriate signature.", var10);
            thread.getThreadGroup().uncaughtException(thread, wrappedEx);
        } catch (Exception var11) {
            thread.getThreadGroup().uncaughtException(thread, var11);
        }
    }
}
