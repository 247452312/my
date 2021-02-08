package indi.uhyils.util;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 09时08分
 */
public class ByteJavaFileObject extends SimpleJavaFileObject {

    /**
     * 存放编译后的字节码
     */
    private ByteArrayOutputStream outPutStream;

    public ByteJavaFileObject(String className, Kind kind) {
        super(URI.create("string:///" + className.replaceAll("\\.", "/") + Kind.SOURCE.extension), kind);
    }

    /**
     * StringJavaFileManage 编译之后的字节码输出会调用该方法（把字节码输出到outputStream）
     *
     * @return
     */
    @Override
    public OutputStream openOutputStream() {
        outPutStream = new ByteArrayOutputStream();
        return outPutStream;
    }

    /**
     * 在类加载器加载的时候需要用到
     *
     * @return
     */
    public byte[] getCompiledBytes() {
        return outPutStream.toByteArray();
    }

}
