package indi.uhyils.loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月16日 10时00分
 */
public class DynamicRunnerClassLoader extends URLClassLoader {

    private final ClassLoader rootClassLoader;

    public DynamicRunnerClassLoader() {
        this(new URL[0], DynamicRunnerClassLoader.class.getClassLoader());
    }

    public DynamicRunnerClassLoader(URL[] urls, ClassLoader classLoader) {
        super(urls, classLoader);
        ClassLoader cl = classLoader;
        while (cl.getParent() != null) {
            cl = cl.getParent();
        }
        this.rootClassLoader = cl;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            if (this.rootClassLoader != null) {
                return this.rootClassLoader.loadClass(name);
            }
        } catch (Exception var4) {
        }

        if (checkFirst()) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader.getClass().getName().equals(DynamicClassLoader.class.getName())) {
                return contextClassLoader.loadClass(name);
            }
        }
        Class<?> loadedClass = this.findLoadedClass(name);

        if (loadedClass == null) {
            loadedClass = this.doLoadClass(name);
        }
        if (resolve) {
            this.resolveClass(loadedClass);
        }

        return loadedClass;


    }

    private boolean checkFirst() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length <= 3) {
            return false;
        }
        StackTraceElement stackTraceElement = stackTrace[5];
        return !stackTraceElement.getClassName().equals(DynamicClassLoader.class.getName());
    }

    private Class<?> doLoadClass(String name) throws ClassNotFoundException {
        try {
            if (this.rootClassLoader != null) {
                return this.rootClassLoader.loadClass(name);
            }
        } catch (Exception var4) {
        }
        try {
            return this.findClass(name);
        } catch (Exception var3) {
            return super.loadClass(name, false);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
