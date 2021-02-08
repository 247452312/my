package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 09时14分
 */

import java.util.Map;

/**
 * 自定义类加载器, 用来加载动态的字节码
 */
public class StringClassLoader extends ClassLoader {
    /**
     * 存放编译之后的字节码(key:类全名,value:编译之后输出的字节码)
     */
    private Map<String, ByteJavaFileObject> javaFileObjectMap;

    public StringClassLoader(Map<String, ByteJavaFileObject> javaFileObjectMap) {
        this.javaFileObjectMap = javaFileObjectMap;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        ByteJavaFileObject fileObject = javaFileObjectMap.get(name);
        if (fileObject != null) {
            byte[] bytes = fileObject.getCompiledBytes();
            return defineClass(name, bytes, 0, bytes.length);
        }
        try {
            return ClassLoader.getSystemClassLoader().loadClass(name);
        } catch (Exception e) {
            return super.findClass(name);
        }
    }
}
