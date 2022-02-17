package indi.uhyils.loader;

import indi.uhyils.context.DynamicContext;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代码 类加载器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 21时00分
 */
public class DynamicClassLoader extends URLClassLoader {

    /**
     * 动态代码组id
     */
    private final Integer groupId;

    /**
     * class全名 to 编译结果
     */
    private Map<String, byte[]> classBytes = new HashMap<>();

    /**
     * class 全名 to class本身
     */
    private Map<String, Class<?>> classMap = new HashMap<>();

    /**
     * 上一个classLoader
     */
    private DynamicClassLoader lastClassLoader;

    /**
     * 原始classLoader
     */
    private ClassLoader originalClassLoader;

    public DynamicClassLoader(Map<String, byte[]> classBytes, Integer groupId) {
        super(new URL[0], DynamicClassLoader.class.getClassLoader());
        this.classBytes.putAll(classBytes);
        this.groupId = groupId;
        ClassLoader classLoader = DynamicClassLoader.class.getClassLoader();
        if (classLoader instanceof DynamicClassLoader) {
            this.lastClassLoader = (DynamicClassLoader) classLoader;
            this.originalClassLoader = lastClassLoader.originalClassLoader;
        } else {
            this.originalClassLoader = classLoader;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        Class<?> resultClass = defineClass(name, buf, 0, buf.length);
        classMap.put(name, resultClass);
        return resultClass;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> aClass = classMap.get(name);
        if (aClass == null) {
            byte[] bytes = classBytes.get(name);
            if (bytes != null) {
                return findClass(name);
            } else {
                Boolean canAppLoad = DynamicContext.CAN_APP_LOAD.get();
                DynamicContext.CAN_APP_LOAD.set(true);
                try {
                    return super.loadClass(name);
                } finally {
                    DynamicContext.CAN_APP_LOAD.set(canAppLoad);
                }
            }
        } else {
            return aClass;
        }
    }

    public Integer getGroupId() {
        return groupId;
    }


}
