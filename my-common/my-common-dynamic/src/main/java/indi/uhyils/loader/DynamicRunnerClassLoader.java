package indi.uhyils.loader;

import indi.uhyils.context.DynamicContext;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月16日 10时00分
 */
public class DynamicRunnerClassLoader extends URLClassLoader {

    public DynamicRunnerClassLoader() {
        super(new URL[0], DynamicRunnerClassLoader.class.getClassLoader());
        // 默认不能用此类加载
        DynamicContext.CAN_APP_LOAD.set(false);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        int i = 1;
        Boolean aBoolean = DynamicContext.CAN_APP_LOAD.get();
        if (aBoolean) {
            return super.loadClass(name);
        } else {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader instanceof DynamicClassLoader) {
                return contextClassLoader.loadClass(name);
            }
            return super.loadClass(name);
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
