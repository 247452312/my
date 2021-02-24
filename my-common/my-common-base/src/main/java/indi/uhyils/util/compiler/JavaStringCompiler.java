package indi.uhyils.util.compiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 字符串编译器(以下内容来自网络)
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年02月25日 07时18分
 * @Version 1.0
 */
public class JavaStringCompiler {

    /**
     * java编译器
     */
    JavaCompiler compiler;
    /**
     * 编译内容管理器
     */
    StandardJavaFileManager stdManager;

    public JavaStringCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.stdManager = compiler.getStandardFileManager(null, null, null);
    }

    /**
     * 在内存中编译String类型的java源码
     *
     * @param fileName java文件名称, e.g. "Test.java"
     * @param source   文件内容
     * @return 编译结果为Map，其中包含类名作为键,二进制java源码作为value
     * @throws IOException 编译失败.
     */
    public Map<String, byte[]> compile(String fileName, String source) throws IOException {
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
            CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            Boolean result = task.call();
            if (result == null || !result) {
                throw new RuntimeException("Compilation failed.");
            }
            return manager.getClassBytes();
        }
    }

    /**
     * 在内存中编译String类型的java源码
     *
     * @param fileSourceMap key是java文件名称, e.g. "Test.java" value是文件内容
     * @return 编译结果为Map，其中包含类名作为键,二进制java源码作为value
     * @throws IOException 编译失败.
     */
    public Map<String, byte[]> compile(Map<String, String> fileSourceMap) throws IOException {
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            List<JavaFileObject> javaFiles = new ArrayList<>(fileSourceMap.size());
            for (Map.Entry<String, String> fileSourceEntry : fileSourceMap.entrySet()) {
                JavaFileObject javaFileObject = manager.makeStringSource(fileSourceEntry.getKey(), fileSourceEntry.getValue());
                javaFiles.add(javaFileObject);
            }
            CompilationTask task = compiler.getTask(null, manager, null, null, null, javaFiles);
            Boolean result = task.call();
            if (result == null || !result) {
                throw new RuntimeException("Compilation failed.");
            }
            return manager.getClassBytes();
        }
    }

    /**
     * 加载在内存中编译好的java源文件
     *
     * @param name       class全名
     * @param classBytes 编译结果
     * @return class们
     * @throws ClassNotFoundException 类没找到
     * @throws IOException            如果加载失败
     */
    public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
        try (MemoryClassLoader classLoader = new MemoryClassLoader(classBytes)) {
            return classLoader.loadClass(name);
        }
    }

}
