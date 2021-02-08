package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年02月07日 09时09分
 */

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

/**
 * 自定义一个字符串的源码对象
 */
public class StringJavaFileObject extends SimpleJavaFileObject {
    /**
     * 等待编译的源码字段
     */
    private String contents;

    /**
     * java源代码 => StringJavaFileObject对象 的时候使用
     *
     * @param className
     * @param contents
     */
    public StringJavaFileObject(String className, String contents) {
        super(URI.create("string:///" + className.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.contents = contents;
    }

    /**
     * 字符串源码会调用该方法
     *
     * @param ignoreEncodingErrors
     * @return
     * @throws IOException
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return contents;
    }

}
