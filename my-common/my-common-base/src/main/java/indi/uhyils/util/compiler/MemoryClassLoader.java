package indi.uhyils.util.compiler;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载编译好的class
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年02月25日 07时19分
 * @Version 1.0
 */
public class MemoryClassLoader extends URLClassLoader {

    /**
     * class全名 to 编译结果
     */
    Map<String, byte[]> classBytes = new HashMap<>();

    public MemoryClassLoader(Map<String, byte[]> classBytes) {
        super(new URL[0], MemoryClassLoader.class.getClassLoader());
        this.classBytes.putAll(classBytes);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        return defineClass(name, buf, 0, buf.length);
    }
}
