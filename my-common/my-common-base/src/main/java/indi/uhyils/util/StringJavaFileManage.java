package indi.uhyils.util;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 09时07分
 */
public class StringJavaFileManage extends ForwardingJavaFileManager {
    /**
     * 存放编译之后的字节码(key:类全名,value:编译之后输出的字节码)
     */
    private Map<String, ByteJavaFileObject> javaFileObjectMap;

    StringJavaFileManage(JavaFileManager fileManager,Map<String, ByteJavaFileObject> javaFileObjectMap) {
        super(fileManager);
        this.javaFileObjectMap = javaFileObjectMap;
    }

    //获取输出的文件对象，它表示给定位置处指定类型的指定类。
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        ByteJavaFileObject javaFileObject = new ByteJavaFileObject(className, kind);
        javaFileObjectMap.put(className, javaFileObject);
        return javaFileObject;
    }

}
