package indi.uhyils.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月15日 12时24分
 */
public class TestClassLoader extends URLClassLoader {

    private final Map<String, byte[]> bytes;


    /**
     * class 全名 to class本身
     */
    private Map<String, Class<?>> classMap = new HashMap<>();

    public TestClassLoader(Map<String, byte[]> bytes) {
        super(new URL[0], DynamicClassLoader.class.getClassLoader());
        this.bytes = bytes;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = bytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        bytes.remove(name);
        Class<?> resultClass = defineClass(name, buf, 0, buf.length);
        classMap.put(name, resultClass);
        return resultClass;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> aClass = classMap.get(name);
        if (aClass == null) {
            byte[] bytess = bytes.get(name);
            if (bytess != null) {
                return findClass(name);
            } else {
                return super.loadClass(name);
            }
        } else {
            return aClass;
        }
    }
}
